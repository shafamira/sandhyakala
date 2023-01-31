package code.constant;

import gui.main.MainPage;

import java.net.URL;

public class FileLocation {
    private static ClassLoader mainClass = MainPage.class.getClassLoader();
    
    public static String TEXT_FOLDER = "textfile";
    
    public static String CUSTOMER_FILENAME = "Customer.txt";
    public static String PRODUCT_FILENAME = "Product.txt";
    public static String STORE_FILENAME = "StoreInfo.txt";
    
    public static URL CUSTOMER_PATH = mainClass.getResource(TEXT_FOLDER + "/" + CUSTOMER_FILENAME);
    public static URL PRODUCT_PATH = mainClass.getResource(TEXT_FOLDER + "/" + PRODUCT_FILENAME);
    public static URL STORE_PATH = mainClass.getResource(TEXT_FOLDER + "/" + STORE_FILENAME);
}
