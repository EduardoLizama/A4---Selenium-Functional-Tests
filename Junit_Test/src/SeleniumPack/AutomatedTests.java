package SeleniumPack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AutomatedTests {

	// Declaracion de variables
	static WebDriver driver;		// Declaracion de webdriver a utilizar para las pruebas
	
	// Metodo para resetear a estado de cuenta anonima sin tener que cerrar el driver actual
	// Con este metodo deslogueamos una cuenta logueada o, en caso de no haber cuenta logueada,
	// logueamos con una cuenta de control y luego la deslogueamos.
	static void resetLogin (WebDriver driver) {
		
		int timeout = 10;
		
		WebElement button;
		WebElement username;
		WebElement password;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		if (driver.findElements(By.className("logout")).size() != 0)
		{
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
		}else if (driver.findElements(By.className("login")).size() != 0 ) 
		{
			
			button = driver.findElement(By.className("login"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
			
			username = driver.findElement(By.id("email"));
			username.sendKeys("randommail12312301@gmail.com");
			password = driver.findElement(By.id("passwd"));
			password.sendKeys("TestPassword");
			button = driver.findElement(By.id("SubmitLogin"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
			if (driver.findElements(By.className("logout")).size() != 0)
			{
				
				fail();
				
			} 
		}
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
	}

	// Metodo para verificar si existe una cuenta logueada
	// Este metodo revisa si existe una cuenta logueada en la pagina y de haberla, la desloguea.
	static void checkLogin (WebDriver driver) {
		
		int timeout = 10;
		
		WebElement button;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		if (driver.findElements(By.className("logout")).size() != 0)
		{
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
		}
		
	}
	
	// Metodo para inicializar el driver
	// El siguiente metodo inicializa el driver relacionandolo con el correcto tipo de navegador y estableciendo la
	// ruta al archivo de driver.exe, crea una nueva instancia del driver, la maximiza y va a la URL del sitio de prueba
	@BeforeClass
	public static void initDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Shakalaka\\Desktop\\Web Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://automationpractice.com/index.php");
	}
	
	//Bateria de tests funcionales para la página web 
	
	// ==========================================================
	//	Tests
	// =================================================================================================
	
	// 1.- Test Register
	//		Escenario prueba: Navegar desde el homepage hasta la pagina de registro e iniciar el proceso de registro
	//						  rellenando los campos necesarios y realizando el registro de la cuenta.
	//		Entradas test: Información de registro de usuario
	//		Salida esperada test: Pagina de informacion de usuario.
	
	// 2.- Test Login
	//		Escenario prueba: Navegar desde el homepage hasta la pagina de logino e iniciar el proceso de login
	//						  rellenando los campos necesarios de credenciales y realizando el login de la cuenta.
	//		Entradas test: Credenciales de login de usuario.
	//		Salida esperada test: Boton de deslogueo habilitado.
	
	// 3.- Test AddCart
	//		Escenario prueba: Seleccionar desde el homepage el item 'Faded Short Sleeve T-shirts' y luego agregar
	//						  al carrito a travez del boton correspondiente en la pagina del item en una cuenta anonima.
	//		Entradas test: Acciones de botones.
	//		Salida esperada test: Cantidad de items en el carrito igual a 1.

	// 4.- Test RemoveCart
	//		Escenario prueba: Seleccionar desde el homepage el item 'Faded Short Sleeve T-shirts' y luego agregar
	//						  al carrito a travez del boton correspondiente en la pagina del item en una cuenta anonima
	//						  luego quitar el item usando el boton correspondiente en el carrito.
	//		Entradas test: Acciones de botones.
	//		Salida esperada test: Cantidad de items en el carrito igual a 0.
	
	// 5.- Test testAddQuantity
	//		Escenario prueba: Seleccionar desde el homepage el item 'Faded Short Sleeve T-shirts' y luego agregar
	//						  al carrito a travez del boton correspondiente en la pagina del item en una cuenta anonima
	//						  luego usando el boton correspondiente aumentar la cantidad del item en 1.
	//		Entradas test: Acciones de botones.
	//		Salida esperada test: Cantidad de items en el carrito igual a 2.
	
	// 6.- Test testRemoveQuantity
	//		Escenario prueba: Seleccionar desde el homepage el item 'Faded Short Sleeve T-shirts' y luego agregar
	//						  al carrito a travez del boton correspondiente en la pagina del item en una cuenta anonima
	//						  luego usando el boton correspondiente reducir la cantidad del item en 1.
	//		Entradas test: Acciones de botones.
	//		Salida esperada test: Pagina de carrito vacio.
	
	// 7.- Test testForgotPassword
	//		Escenario prueba: Navegar desde el homepage hacia la pagina de login y luego seleccionar la opcion 
	//						  "olvide mi contraseña", luego llenar el campo de email y presionar el boton para terminar
	//						  el proceso de recuperacion
	//		Entradas test: Email.
	//		Salida esperada test: Mensaje de confirmacion de recuperacion de contraseña.
	
	// 8.- Test testForgotPassword
	//		Escenario prueba: Navegar desde el homepage hacia la pagina de login y luego seleccionar la opcion 
	//						  "olvide mi contraseña", luego llenar el campo de email y presionar el boton para terminar.
	//						  el proceso de recuperacion
	//		Entradas test: Email.
	//		Salida esperada test: Mensaje de confirmacion de recuperacion de contraseña.
	
	// 9.- Test testForgotPassword2
	//		Escenario prueba: Navegar desde el homepage hacia la pagina de login y luego seleccionar la opcion 
	//						  "olvide mi contraseña", luego llenar el campo de email (con un email no registrado en pagina) 
	//						  y presionar el boton para terminar el proceso de recuperacion.
	//		Entradas test: Email.
	//		Salida esperada test: Mensaje de error (email invalido) de recuperacion de contraseña.
	
	// 9.- Test testPayment
	//	Escenario prueba: Seleccionar desde el homepage el item 'Faded Short Sleeve T-shirts' y luego agregar
	//					  al carrito a travez del boton correspondiente en la pagina del item en una cuenta anonima,
	//					  presionar boton "proceed to checkout", iniciar y completar proceso de login, llenar campo de texo
	//					  de instrucciones especiales, confirmar orden, presionar el boton para aceptar el ToS, presionar confirmar
	//					  seleccionar medio de pago por banco terminando el proceso de pago.
	//		Entradas test: Credenciales de login, texto de instrucciones, acciones de botones.
	//		Salida esperada test: Mensaje de confirmacion de pago.
	
	// 10.- Test testContactUs
	//		Escenario prueba: Navegar desde el homepage hacia la pagina de contacto, llenar los campos obligatorios y  
	//						  y seleccionar el boton para confirmar el proceso de envio de mensaje.
	//		Entradas test: Email, mensaje, seleccion de heading.
	//		Salida esperada test: Mensaje de confirmacion de mensaje enviado.
	
	// 11.- Test testSearch
	//		Escenario prueba: Llenar el campo de texto del buscador de homepage con la palabra "dress" y presionar el boton
	//						  de busqueda. 
	//		Entradas test: Query de busqueda.
	//		Salida esperada test: Pantalla de busqueda con los 7 items especificos de la categoria "dress" o con "dress" 
	//							  en el nombre/descripcion.

	// 12.- Test testRegister2
	//		Escenario prueba: Igual al proceso de registro de test 1 pero con un email que ya esta registrado. 
	//		Entradas test: Email ya registrado.
	//		Salida esperada test: Mensaje de error (email ya registrado) de registro.
	
	// 13.- Test testLogin2
	//		Escenario prueba: Igual al proceso de login de test 2 pero con credenciales invalidas.
	//		Entradas test: Credenciales de login invalidas.
	//		Salida esperada test: Mensaje de error (credenciales invalidas) de login.

	
	// Test Register
	@Test
	public void testRegister() {
		
		int timeout = 10;
		
		WebElement button;
		WebElement email;
		WebElement text;
		WebElement dropmenu;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		String currentURL;
		String expectedURL;
		String expectedMessage;
		String actualMessage;
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		checkLogin(driver);
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		
		button = driver.findElement(By.className("login"));
		button.click();
		email = driver.findElement(By.id("email_create"));
		//email.sendKeys("G6-RTestEmail001@gmail.com");
		email.sendKeys("randommail12312314@gmail.com");
		button = driver.findElement(By.id("SubmitCreate"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1")));
		
		dropmenu = driver.findElement(By.id("id_state"));
		Select dropdown = new Select(dropmenu);
		
		wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>(){
	        public Boolean apply(WebDriver driver)  
	        {
	            Select select = new Select(driver.findElement(By.id("id_state")));
	            return select.getOptions().size()>5;
	        }
	    });
		
		button = driver.findElement(By.id("id_gender1"));
		button.click();
		text = driver.findElement(By.id("customer_firstname"));
		text.sendKeys("Test First Name");
		text = driver.findElement(By.id("customer_lastname"));
		text.sendKeys("Test Last Name");
		text = driver.findElement(By.id("passwd"));
		text.sendKeys("TestPassword");		
		text = driver.findElement(By.id("address1"));
		text.sendKeys("Test Address");
		text = driver.findElement(By.id("city"));
		text.sendKeys("Test City");
		dropdown.selectByIndex(5);
		text = driver.findElement(By.id("postcode"));
		text.sendKeys("90212");
		text = driver.findElement(By.id("phone_mobile"));
		text.sendKeys("123456789");
		button = driver.findElement(By.id("submitAccount"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
		
		expectedURL = "http://automationpractice.com/index.php?controller=my-account";
		currentURL = driver.getCurrentUrl();
		assertEquals(expectedURL, currentURL);
		
		expectedMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
		actualMessage = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText();
		assertEquals(expectedMessage, actualMessage);
		
	}

	// Test Login
	
	// Test Login
	@Test
	public void testLogin() {
		
		int timeout = 10;
		
		WebElement button;
		WebElement username;
		WebElement password;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		checkLogin(driver);
		
		button = driver.findElement(By.className("login"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
		
		username = driver.findElement(By.id("email"));
		username.sendKeys("randommail12312301@gmail.com");
		password = driver.findElement(By.id("passwd"));
		password.sendKeys("TestPassword");
		button = driver.findElement(By.id("SubmitLogin"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
		
		if (driver.findElements(By.className("logout")).size() == 0)
		{
			
			fail();
			
		} 
		
	}

	// Add element to cart
	
	// Test Add to Cart
	@Test
	public void testAddCart() {
		
		int timeout = 10;
		
		WebElement button;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		resetLogin(driver);
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		
		button = driver.findElement(By.xpath("//*[@alt='Faded Short Sleeve T-shirts']"));
		button.click();
		button = driver.findElement(By.id("add_to_cart"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
		
		driver.findElement(By.id("layer_cart")).isDisplayed();
		
	}

	// Remove element from cart
	
	// Test Remove from Cart
	@Test
	public void testRemoveCart() {
		
		int timeout = 10;
		
		WebElement button;
		WebElement table;
		WebElement waitflag;
		WebElement username;
		WebElement password;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		if (driver.findElements(By.className("logout")).size() != 0)
		{
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
		}else if (driver.findElements(By.className("login")).size() != 0 ) 
		{
			
			button = driver.findElement(By.className("login"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
			
			username = driver.findElement(By.id("email"));
			username.sendKeys("randommail12312301@gmail.com");
			password = driver.findElement(By.id("passwd"));
			password.sendKeys("TestPassword");
			button = driver.findElement(By.id("SubmitLogin"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
			if (driver.findElements(By.className("logout")).size() != 0)
			{
				
				fail();
				
			} 
		}
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		
		button = driver.findElement(By.xpath("//*[@alt='Faded Short Sleeve T-shirts']"));
		button.click();
		button = driver.findElement(By.id("add_to_cart"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
		
		driver.navigate().to("http://automationpractice.com/index.php?controller=order");
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_summary")));
		
		button = driver.findElement(By.className("icon-trash"));
		button.click();
		
		if (driver.findElement(By.id("layer_cart")).isDisplayed()) {
			
			fail();
		}
		
	}
	
	// Test Add Quantity Cart-Item
	
	// Test Add Quantity of Item
	@Test
	public void testAddQuantity() {
		
		int timeout = 10;
		String counter;
		
		WebElement button;
		WebElement table;
		WebElement waitflag;
		WebElement username;
		WebElement password;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		if (driver.findElements(By.className("logout")).size() != 0)
		{
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
		}else if (driver.findElements(By.className("login")).size() != 0 ) 
		{
			
			button = driver.findElement(By.className("login"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
			
			username = driver.findElement(By.id("email"));
			username.sendKeys("randommail12312301@gmail.com");
			password = driver.findElement(By.id("passwd"));
			password.sendKeys("TestPassword");
			button = driver.findElement(By.id("SubmitLogin"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
			if (driver.findElements(By.className("logout")).size() != 0)
			{
				
				fail();
				
			} 
		}
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		
		button = driver.findElement(By.xpath("//*[@alt='Faded Short Sleeve T-shirts']"));
		button.click();
		button = driver.findElement(By.id("add_to_cart"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
		
		driver.navigate().to("http://automationpractice.com/index.php?controller=order");
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_summary")));
		
		button = driver.findElement(By.id("cart_quantity_up_1_1_0_0"));
		button.click();

		driver.navigate().to("http://automationpractice.com/index.php");
		driver.navigate().to("http://automationpractice.com/index.php?controller=order");
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_summary")));
		
		counter = driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a/span[1]")).getText();
		
		assertEquals("2", counter);
		
	}
	
	// Test Remove Quantity Cart
	
	// Test Remove Quantity of Item
	@Test
	public void testRemoveQuantity() {
		
		int timeout = 5;
		String counter;
		
		WebElement button;
		WebElement table;
		WebElement waitflag;
		WebElement username;
		WebElement password;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.navigate().to("http://automationpractice.com/index.php");

		if (driver.findElements(By.className("logout")).size() != 0)
		{
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
		}else if (driver.findElements(By.className("login")).size() != 0 ) 
		{
			
			button = driver.findElement(By.className("login"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
			
			username = driver.findElement(By.id("email"));
			username.sendKeys("randommail12312301@gmail.com");
			password = driver.findElement(By.id("passwd"));
			password.sendKeys("TestPassword");
			button = driver.findElement(By.id("SubmitLogin"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
			
			if (driver.findElements(By.className("logout")).size() != 0)
			{
				
				fail();
				
			} 
		}
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		
		button = driver.findElement(By.xpath("//*[@alt='Faded Short Sleeve T-shirts']"));
		button.click();
		button = driver.findElement(By.id("add_to_cart"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
		
		driver.navigate().to("http://automationpractice.com/index.php?controller=order");
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_summary")));
		
		button = driver.findElement(By.id("cart_quantity_down_1_1_0_0"));
		button.click();

		if (driver.findElement(By.id("layer_cart")).isDisplayed()) {
			
			fail();
		}
		
	}
	
	// Test Forgot Password
	
	// Test Forgot Password
	@Test
	public void testForgotPassword() {
		
		int timeout = 5;
		
		WebElement button;
		WebElement email;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		if (driver.findElements(By.className("logout")).size() != 0)
		{
			
			button = driver.findElement(By.className("logout"));
			button.click();
			
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.className("login")));
			
		}
		
		button = driver.findElement(By.className("login"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.id("SubmitLogin")));
		
		button = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div/p[1]/a"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//*[@id=\"email\"]")));
		
		email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		email.sendKeys("randommail123123@gmail.com");
		
		button = driver.findElement(By.xpath("//*[@id=\"form_forgotpassword\"]/fieldset/p/button"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//*[@id=\"center_column\"]/div/p")));
		
		if (!driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p")).isDisplayed())
		{
			
			fail();
		}
	}
	
	// Test Forgot Password (invalid Email)
	
	// Test Forgot Password with invalid email
	@Test	
	public void testForgotPassword2() {
			
		int timeout = 5;
			
		WebElement button;
		WebElement email;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
			
		driver.navigate().to("http://automationpractice.com/index.php");
			
		if (driver.findElements(By.className("logout")).size() != 0)
		{
				
			button = driver.findElement(By.className("logout"));
			button.click();
				
			waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.className("login")));
				
		}
			
		button = driver.findElement(By.className("login"));
		button.click();
			
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.id("SubmitLogin")));
			
		button = driver.findElement(By.xpath("//*[@id=\"login_form\"]/div/p[1]/a"));
		button.click();
			
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//*[@id=\"email\"]")));
			
		email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		email.sendKeys("obviouslywrong@gmail.com");
			
		button = driver.findElement(By.xpath("//*[@id=\"form_forgotpassword\"]/fieldset/p/button"));
		button.click();
			
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//*[@id=\"center_column\"]/div/div")));
			
		if (!driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/div")).isDisplayed())
		{
				
			fail();
		}
	}
	
	// Process Payment
	
	// Test Payment
	@Test
	public void testPayment() {
		
		int timeout = 5;
		
		String actualconfirmation;
		WebElement button;
		WebElement waitflag;
		WebElement email;
		WebElement password;
		WebElement message;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		resetLogin(driver);
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.className("login")));
		
		button = driver.findElement(By.xpath("//*[@alt='Faded Short Sleeve T-shirts']"));
		button.click();
		button = driver.findElement(By.id("add_to_cart"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
		
		driver.navigate().to("http://automationpractice.com/index.php?controller=order");
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart_summary")));
		
		button = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
		
		email = driver.findElement(By.id("email"));
		email.sendKeys("randommail12312301@gmail.com");
		password = driver.findElement(By.id("passwd"));
		password.sendKeys("TestPassword");
		
		button = driver.findElement(By.id("SubmitLogin"));
		button.click();
		
		message = driver.findElement(By.xpath("//*[@id=\"ordermsg\"]/textarea"));
		message.sendKeys("Test Message");
		
		button = driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button"));
		button.click();
		
		button = driver.findElement(By.id("cgv"));
		button.click();
		
		button = driver.findElement(By.xpath("//*[@id=\"form\"]/p/button"));
		button.click();
		
		button = driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p"));
		button.click();
		
		button = driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button"));
		button.click();
		
		actualconfirmation = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p/strong")).getText();
		String expectedconfirmation = "Your order on My Store is complete.";
		
		assertEquals(actualconfirmation, expectedconfirmation);
		
	}
	
	// Test Contact us
	
	// Test ContactUs
	@Test
	public void testContactUs() {
		
		int timeout = 5;
		
		resetLogin(driver);
		
		WebElement button;
		WebElement email;
		WebElement message;
		WebElement dropmenu;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		button = driver.findElement(By.id("contact-link"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.id("submitMessage")));
		
		dropmenu = driver.findElement(By.id("id_contact"));
		Select dropdown = new Select(dropmenu);
		dropdown.selectByIndex(1);
		
		email = driver.findElement(By.id("email"));
		email.sendKeys("randommail123123@gmail.com");
		
		message = driver.findElement(By.id("message"));
		message.sendKeys("Test Message");
		
		button = driver.findElement(By.id("submitMessage"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//*[@id=\"center_column\"]/p")));
		
		if (!driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).isDisplayed())
		{
			
			fail();
		}

	}
	
	// Test Search
	@Test
	public void testSearch() {
		
		WebElement search;
		WebElement button;
		String actualTitle1;
		String actualTitle2;
		String actualTitle3;
		String actualTitle4;
		String actualTitle5;
		String actualTitle6;
		String actualTitle7;
		
		search = driver.findElement(By.id("search_query_top"));
		search.sendKeys("Dress");
		
		button = driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button"));
		button.click();
		
		String expectedTitle1 = "Printed Summer Dress";
		String expectedTitle2 = "Printed Dress";
		String expectedTitle3 = "Printed Summer Dress";
		String expectedTitle4 = "Printed Chiffon Dress";
		String expectedTitle5 = "Printed Dress";
		String expectedTitle6 = "Faded Short Sleeve T-shirts";
		String expectedTitle7 = "Blouse";
		
		
		actualTitle1 = driver.findElement(By.xpath
				("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).getAttribute("title");
		actualTitle2 = driver.findElement(By.xpath
				("//*[@id=\"center_column\"]/ul/li[2]/div/div[1]/div/a[1]/img")).getAttribute("title");
		actualTitle3 = driver.findElement(By.xpath
				("//*[@id=\"center_column\"]/ul/li[3]/div/div[1]/div/a[1]/img")).getAttribute("title");
		actualTitle4 = driver.findElement(By.xpath
				("//*[@id=\"center_column\"]/ul/li[4]/div/div[1]/div/a[1]/img")).getAttribute("title");
		actualTitle5 = driver.findElement(By.xpath
				("//*[@id=\"center_column\"]/ul/li[5]/div/div[1]/div/a[1]/img")).getAttribute("title");
		actualTitle6 = driver.findElement(By.xpath
				("//*[@id=\"center_column\"]/ul/li[6]/div/div[1]/div/a[1]/img")).getAttribute("title");
		actualTitle7 = driver.findElement(By.xpath
				("//*[@id=\"center_column\"]/ul/li[7]/div/div[1]/div/a[1]/img")).getAttribute("title");
		
		assertEquals(expectedTitle1,actualTitle1);
		assertEquals(expectedTitle2,actualTitle2);
		assertEquals(expectedTitle3,actualTitle3);
		assertEquals(expectedTitle4,actualTitle4);
		assertEquals(expectedTitle5,actualTitle5);
		assertEquals(expectedTitle6,actualTitle6);
		assertEquals(expectedTitle7,actualTitle7);
	}
	
	// Test Register with used Email
	@Test
	public void testRegister2() {
		
		int timeout = 10;
		
		WebElement button;
		WebElement email;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		String expectedError;
		String actualError;
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		checkLogin(driver);
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login")));
		
		button = driver.findElement(By.className("login"));
		button.click();
		email = driver.findElement(By.id("email_create"));
		//email.sendKeys("G6-TestEmail001@gmail.com");
		email.sendKeys("randommail123123@gmail.com");
		button = driver.findElement(By.id("SubmitCreate"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"create_account_error\"]/ol/li")));
		
		actualError = driver.findElement(By.xpath("//*[@id=\"create_account_error\"]/ol/li")).getText();
		expectedError = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
		
		assertEquals (actualError,expectedError);
		
	}
	
	// Test Login with wrong credentials
	@Test
	public void testLogin2() {
		
		int timeout = 10;
		
		WebElement button;
		WebElement username;
		WebElement password;
		WebElement waitflag;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		String expectedError;
		String actualError;
		
		driver.navigate().to("http://automationpractice.com/index.php");
		
		checkLogin(driver);
		
		button = driver.findElement(By.className("login"));
		button.click();
		
		waitflag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubmitLogin")));
		
		username = driver.findElement(By.id("email"));
		username.sendKeys("randommail12312301@gmail.com");
		password = driver.findElement(By.id("passwd"));
		password.sendKeys("WrongPassword");
		button = driver.findElement(By.id("SubmitLogin"));
		button.click();
		
		actualError = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText();
		expectedError = "Authentication failed.";
		
		assertEquals(actualError, expectedError);
		
	}
	
	// Metodo para finalizar el testeo
	// Luego de realizar todos los tests se cierra el driver.
	@AfterClass
	public static void Finalize()
	{
		driver.quit();
	}
	
}
