package automationFramework;


//Importa as bibliotecas do JUnit, Selenium WebDriver
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


//Nota��o chamada @RunWith, que indica ao script de teste que ele deve se preparar para executar com par�metros (Data Driven)
@RunWith(Parameterized.class)

//Classe JUnit que cont�m os m�todos de teste
public class EmailTest  {
	
	private  EmailPage emailPage;
	private WebDriver driver;
	// Declarando a vari�vel que recebe a url base de acesso
	private  String baseUrl;
	
	//Atributos que ter�o a responsabilidade de passar os dados da fonte de dados para o script.
	private String email;
	private String senha;
	private String assunto;
	private String corpoEmail;
	
	// Construtor � o respons�vel direto de receber os dados dinamicamente da fonte de dados e passar os mesmos para os atributos.
	public EmailTest(String email, String senha, String assunto, String corpoEmail){
		this.email = email;
		this.senha = senha;
		this.assunto = assunto;
		this.corpoEmail =corpoEmail;
	}



// M�todo que inicia tudo que for necess�rio para o teste 
// Cria uma inst�ncia do navegador e abre a p�gina inicial do Gmail.
@Before
public void setUpTest() throws Exception {
	
	System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
	driver = new ChromeDriver();
	emailPage = new EmailPage(driver);
	baseUrl = "https://mail.google.com/";
	driver.get(baseUrl);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}



//M�todo que testa efetuar login no Gmail, enviar email para uma conta e validar o email enviado.
 @Test
public void testaEnviarEmailContaGmail() throws Exception {
	  
	//Efetua o login 
	emailPage.Logar(email, senha);
	
	//Envia o email
    emailPage.EnviarEmail(email, assunto, corpoEmail);  
	
	//Valida o email enviado
    emailPage.ValidarEmailEnviado(email, assunto, corpoEmail); 
	
}

// M�todo que finaliza o teste, fechando a inst�ncia do WebDriver. 
@After
public void tearDownTest(){
    driver.quit();
}

//Fonte de dados. A anota��o @Parameters diz para o m�todo que ele conter� os registros necess�rios para a execu��o, onde este m�todo retorna um array de objetos (os registros que ser�o associados aos atributos na mesma sequencia que eles foram definidos).
@Parameters
public  static Collection<Object[]> data() {
	return Arrays.asList(new Object[][] {
			{"danixdosantos@gmail.com", "Gatinho10", "assunto1", "corpo email 1"},	
			{"danixdosantos@gmail.com", "Gatinho10", "assunto2", "corpo email 2"},
	});
}

}
