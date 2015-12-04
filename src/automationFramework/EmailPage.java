package automationFramework;

//Importa as bibliotecas do JUnit, Selenium WebDriver, etc
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class EmailPage {
	private final WebDriver driver;

	public EmailPage(WebDriver driver) {
		this.driver = driver;     
	}

	// Atribuição dos elementos da tela
	By usernameLocator = By.id("Email");
	By passwordLocator = By.id("Passwd");
	By loginButtonLocator = By.id("signIn");
	By sigInButtonLocator = By.id("gmail-sign-in");
	By nextButtonLocator = By.id("next");

	By escreverButtonLocator = By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[1]/div/div");
	By toLocator = By.name("to");
	By subjectboxLocator = By.name("subjectbox");
	By corpoButtonLocator = By.xpath("/html/body/div[14]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/table/tbody/tr[1]/td/div/div[1]/div[2]/div[1]/div/table/tbody/tr/td[2]/div[2]/div");
	By enviarButtonLocator = By.xpath("/html/body/div[14]/div/div/div/div[1]/div[3]/div[1]/div[1]/div/div/div/div[3]/div/div/div[4]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/div/div/div[4]/table/tbody/tr/td[1]/div/div[2]");

	By enviadosButtonLocator = By.xpath("/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/div[2]/div/div[1]/div[1]/div/div[4]/div/div/div[1]/span/a");
	By pesquisaLocator = By.name("q");
	By pesquisarButtonLocator = By.id("gbqfb");


	// Permite que o usuário digite seu nome de usuário no campo nome de usuário
	public void typeUsername(String username) {
		driver.findElement(usernameLocator).clear();
		driver.findElement(usernameLocator).sendKeys(username);   
	}

	// Permite que o usuário digite seu password no campo password
	public void typePassword(String password) {
		driver.findElement(passwordLocator).clear();
		driver.findElement(passwordLocator).sendKeys(password);   
	}

	// Permite que o usuário clique no botão entrar no Gmail
	public void entrarNoGmail() {
		driver.findElement(sigInButtonLocator).click();  
	}

	// A página de login permite que o usuário clique no botão Next
	public void nextPasso() {
		driver.findElement(nextButtonLocator).click(); 
	}

	// A página de login permite que o usuário clique no botão efetuar login
	public void submitLogin() {
		driver.findElement(loginButtonLocator).click();  
	}



	// Permite que o usuário digite o email no campo para
	public void typeEmail(String email) {
		driver.findElement(toLocator).clear();
		driver.findElement(toLocator).sendKeys(email);   
	}

	// Permite que o usuário digite o assunto no campo assunto
	public void typeAssunto(String assunto) {
		driver.findElement(subjectboxLocator).clear();
		driver.findElement(subjectboxLocator).sendKeys(assunto);   
	}

	// Permite que o usuário digite o corpo do email no campo texto
	public void typeCorpoEmail(String corpoEmail) {
		driver.findElement(corpoButtonLocator).clear();
		driver.findElement(corpoButtonLocator).sendKeys(corpoEmail);   
	}

	// Permite que o usuário clique no botão escrever
	public void escreverEmail() {
		driver.findElement(escreverButtonLocator).click();  
	}

	// Permite que o usuário clique no botão Enviar email
	public void enviarEmail() {
		driver.findElement(enviarButtonLocator).click(); 
	}



	// Permite que o usuário digite o email a ser pesquisado no campo de pesquisa
	public void typePesquisaEmail(String pesquisaEmail) {
		driver.findElement(pesquisaLocator).clear();
		driver.findElement(pesquisaLocator).sendKeys(pesquisaEmail);   
	}

	// Permite que o usuário clique no botão enviados
	public void enviadosEmail() {
		driver.findElement(enviadosButtonLocator).click();  
	}

	// Permite que o usuário clique no botão pesquisar Email
	public void pesquisarEmail() {
		driver.findElement(pesquisarButtonLocator).click(); 
	}




	// Método que efetua o login
	public void Logar(String username, String password) {
		typeUsername(username);
		nextPasso();
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated((passwordLocator)));
		typePassword(password);
		submitLogin();
	}

	// Método que envia email
	public void EnviarEmail(String email, String assunto, String corpoEmail) {	
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated((escreverButtonLocator)));		
		escreverEmail();		
		wait.until(ExpectedConditions.visibilityOfElementLocated((toLocator)));		
		typeEmail(email);
		typeAssunto(assunto);
		wait.until(ExpectedConditions.visibilityOfElementLocated((corpoButtonLocator)));	
		typeCorpoEmail(corpoEmail);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		enviarEmail();		   
	}

	// Método que valida o email enviado
	public void ValidarEmailEnviado(String email, String assunto, String corpoEmail) {
		enviadosEmail();
		typePesquisaEmail(email);
		pesquisarEmail();
		Assert.assertTrue(driver.getTitle().contains(email));	   
	}
}