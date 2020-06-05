package com.fatec.scel;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class UC01ConsiltaCEP {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void cT01() {
		driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/buscaCep.cfm");
		driver.manage().window().setSize(new Dimension(1382, 744));
		driver.findElement(By.name("UF")).click();
		{
			WebElement dropdown = driver.findElement(By.name("UF"));
			dropdown.findElement(By.xpath("//option[. = 'SP']")).click();
		}
		driver.findElement(By.name("Localidade")).click();
		driver.findElement(By.name("Localidade")).sendKeys("São Paulo");
		driver.findElement(By.name("Tipo")).click();
		{
			WebElement dropdown = driver.findElement(By.name("Tipo"));
			dropdown.findElement(By.xpath("//option[. = 'Rua']")).click();
		}
		driver.findElement(By.name("Tipo")).click();
		driver.findElement(By.name("Logradouro")).click();
		driver.findElement(By.name("Logradouro")).sendKeys("Aguia de Haia");
		driver.findElement(By.cssSelector(".btn2")).click();
		assertThat(driver.findElement(By.cssSelector("p")).getText(), is("DADOS ENCONTRADOS COM SUCESSO."));
	}
	@Test
	public void cT02LogradouroInvalido1() {
		driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/buscaCep.cfm");
		driver.manage().window().setSize(new Dimension(1382, 744));
		driver.findElement(By.name("UF")).click();
		{
			WebElement dropdown = driver.findElement(By.name("UF"));
			dropdown.findElement(By.xpath("//option[. = 'SP']")).click();
		}
		driver.findElement(By.name("Localidade")).click();
		driver.findElement(By.name("Localidade")).sendKeys("São Paulo");
		driver.findElement(By.name("Tipo")).click();
		{
			WebElement dropdown = driver.findElement(By.name("Tipo"));
			dropdown.findElement(By.xpath("//option[. = 'Rua']")).click();
		}
		driver.findElement(By.name("Tipo")).click();
		driver.findElement(By.name("Logradouro")).click();
		driver.findElement(By.name("Logradouro")).sendKeys("xxxxxxxxxxxxxxxxxxx");
		driver.findElement(By.cssSelector(".btn2")).click();
		assertThat(driver.findElement(By.cssSelector("p")).getText(), is("LOGRADOURO NAO ENCONTRADO."));
	}
	  @Test
	  public void cT03SemLogradourto() {
	    driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/buscaCep.cfm");
	    driver.manage().window().setSize(new Dimension(1366, 728));
	    driver.findElement(By.name("UF")).click();
	    {
	      WebElement dropdown = driver.findElement(By.name("UF"));
	      dropdown.findElement(By.xpath("//option[. = 'SP']")).click();
	    }
	    driver.findElement(By.name("UF")).click();
	    driver.findElement(By.name("Localidade")).click();
	    driver.findElement(By.name("Localidade")).sendKeys("São Paulo");
	    driver.findElement(By.name("Tipo")).click();
	    {
	      WebElement dropdown = driver.findElement(By.name("Tipo"));
	      dropdown.findElement(By.xpath("//option[. = 'Rua']")).click();
	    }
	    driver.findElement(By.name("Tipo")).click();
	    driver.findElement(By.name("Logradouro")).click();
	    driver.findElement(By.cssSelector(".contentform")).click();
	    driver.findElement(By.name("Logradouro")).sendKeys("");
	    driver.findElement(By.cssSelector(".btn2")).click();
	    assertThat(driver.switchTo().alert().getText(), is("Informe o logradouro !"));
	  }

}
