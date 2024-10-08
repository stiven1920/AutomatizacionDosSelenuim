package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Floresteria {

	// importo los componentes de selenium
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	void getUp() {
		// instanceo los componentes antes de correr el test
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	@Test
	void testSelectDateAndTime() {
		try {
			//abro la pagina
			driver.get("https://sanangel.com.co/");
			// Espero a que el selector cargue
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector("#destacados-tab > div.vc_tta-panel-body > div > ul > li")));

			//obtengo la lista de elementos de las flores
			List<WebElement> elements = driver
					.findElements(By.cssSelector("#destacados-tab > div.vc_tta-panel-body > div > ul > li"));

			// verifico que elements tenga almenos un elemento y escojo la posicion 0
			if (elements.size() >= 1) {
				WebElement thirdElement = elements.get(0);
				wait.until(ExpectedConditions.visibilityOf(thirdElement)); // verifico que el elemento 0 este cargado
				wait.until(ExpectedConditions.elementToBeClickable(thirdElement)).click(); // doy click en la imagen
			}

			List<WebElement> listElement = driver.findElements(By.cssSelector("div.hour-picker > div.row > div"));

			if (listElement.size() > 1) {
				WebElement secondElement = listElement.get(1);
				wait.until(ExpectedConditions.visibilityOf(secondElement));
				wait.until(ExpectedConditions.elementToBeClickable(secondElement)).click();

				List<WebElement> hourElements = driver.findElements(By.cssSelector(".hour-dropdow-item"));
				if (!hourElements.isEmpty()) {
					WebElement firstHourElement = hourElements.get(1);
					wait.until(ExpectedConditions.elementToBeClickable(firstHourElement)).click();

//					WebElement textDate = wait.until(ExpectedConditions.visibilityOfElementLocated(
//							By.cssSelector("div.row > div:nth-child(3) > div > span.date")));
//					String dateNow = textDate.getAttribute("innerText");
//
//					Assertions.assertTrue(dateNow.contains("9 de octubre"),
//							"La fecha seleccionada no es correcta: " + dateNow);
				}
			}

			WebElement cantidad = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"div.woocommerce-variation-add-to-cart.variations_button.woocommerce-variation-add-to-cart-enabled > input")));
			cantidad.clear();
			cantidad.sendKeys("2");
			WebElement clickCarrito = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
			wait.until(ExpectedConditions.visibilityOf(clickCarrito));
			wait.until(ExpectedConditions.elementToBeClickable(clickCarrito)).click();

			// se vuelve a repetir el proceso 
			driver.get("https://sanangel.com.co/");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector("#destacados-tab > div.vc_tta-panel-body > div > ul > li")));

			List<WebElement> elements2 = driver
					.findElements(By.cssSelector("#destacados-tab > div.vc_tta-panel-body > div > ul > li"));

			if (elements2.size() >= 3) {
				WebElement thirdElement = elements2.get(3);
				wait.until(ExpectedConditions.visibilityOf(thirdElement));
				wait.until(ExpectedConditions.elementToBeClickable(thirdElement)).click();
			}

			List<WebElement> listElement2 = driver.findElements(By.cssSelector("div.hour-picker > div.row > div"));

			if (listElement2.size() > 1) {
				WebElement secondElement = listElement2.get(1);
				wait.until(ExpectedConditions.visibilityOf(secondElement));
				wait.until(ExpectedConditions.elementToBeClickable(secondElement)).click();

				List<WebElement> hourElements = driver.findElements(By.cssSelector(".hour-dropdow-item"));
				if (!hourElements.isEmpty()) {
					WebElement firstHourElement = hourElements.get(1);
					wait.until(ExpectedConditions.elementToBeClickable(firstHourElement)).click();

					WebElement textDate = wait.until(ExpectedConditions.visibilityOfElementLocated(
							By.cssSelector("div.row > div:nth-child(3) > div > span.date")));
					String dateNow = textDate.getAttribute("innerText");
					Assertions.assertTrue(dateNow.contains("9 de octubre"),
							"La fecha seleccionada no es correcta: " + dateNow);
				}
			}

			WebElement cantidad2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
					"input[type='number']")));
			cantidad2.clear();
			cantidad2.sendKeys("5");
			WebElement clickCarrito2 = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
			wait.until(ExpectedConditions.visibilityOf(clickCarrito2));
			wait.until(ExpectedConditions.elementToBeClickable(clickCarrito2)).click();
			
			WebElement textCompra = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector("#post-196 > header > h1")));
			String textFinal = textCompra.getAttribute("innerText");
			
			assertEquals("Finalizar compra", textFinal);

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	@AfterEach
	void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
