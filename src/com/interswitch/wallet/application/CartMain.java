package com.interswitch.wallet.application;

import com.interswitch.wallet.dto.Cart;
import com.interswitch.wallet.model.Customer;
import com.interswitch.wallet.model.Product;
import com.interswitch.wallet.service.WalletService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartMain {
    public static void main(String[] args) throws Exception {
//        registerCustomer();
//        fundCustomer();
//        getCustomerByWallet();
//        createProduct();
//        restockProduct();
//        displayAllProducts();
//          addToCart();
        menu();
//        addToCart();






    }

    public static String registerCustomer() throws Exception {
        WalletService walletService = new WalletService();
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter Customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter Customer Phone number: ");
        String customerPhoneNumber = scanner.nextLine();

        System.out.println("Enter Customer Address: ");
        String customerAddress = scanner.nextLine();


        String walletId = walletService.registerCustomer(customerName,customerPhoneNumber,customerAddress);
        System.out.println("Your wallet ID is:" + walletId);

        return walletId;

    }

    public static Customer fundCustomer() throws Exception {
        WalletService walletService = new WalletService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("To Fund your wallet Account,");
        System.out.println("Enter wallet ID: ");
        String walletId =scanner.nextLine();

        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        return walletService.fundCustomerWallet(walletId,amount);
    }

    public static void getCustomerByWallet(){
        WalletService walletService = new WalletService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Customer ID: ");
        String walletId = scanner.nextLine();

        System.out.println( walletService.getCustomerByWallet(walletId));


    }
    public static void createProduct(){
        WalletService walletService = new WalletService();
        Scanner scanner = new Scanner(System.in);

        boolean status = true;
        List<Product> products = new ArrayList<>();
        while(status){

            System.out.println("Enter product name: ");
            String productName = scanner.nextLine();

            System.out.println("Enter product description: ");
            String productDescription = scanner.nextLine();

            System.out.println("Enter product Unit price: ");
            double productPrice = scanner.nextDouble();

            System.out.println("Enter product quantity: ");
            Integer productQuantity = scanner.nextInt();

            Product product = new Product(productName,productDescription,productPrice,productQuantity);
            products.add(product);

            System.out.println("Enter 1 to continue or 2 quit");
            int result = scanner.nextInt();
            scanner.nextLine();

            if(result == 1){
                status = true;
            } else if (result == 2) {
                status = false;

            }
            walletService.populateProductCatalog(products);


        }

    }

    public static void restockProduct(){
        WalletService walletService = new WalletService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product ID: ");
        Integer productID = scanner.nextInt();

        System.out.println("Enter product quantity: ");
        Integer productQty= scanner.nextInt();

        walletService.restockProduct(productID,productQty);



    }
    public  static void viewProductDetails(){
        WalletService walletService = new WalletService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product ID: ");
        Integer productID = scanner.nextInt();

        walletService.getProductById(productID);


    }
    public static void displayAllProducts(){
        WalletService walletService = new WalletService();
        List<Product> products = walletService.getProductCatalog();
        products.forEach(System.out::println);

    }

    public static void addToCart() throws Exception {
        Scanner scanner = new Scanner(System.in);
        WalletService walletService = new WalletService();

        registerCustomer();
        fundCustomer();
        displayAllProducts();


        boolean status = true;
        while(status) {


            System.out.println("Enter Product ID: ");
            Integer productID = scanner.nextInt();

            System.out.println("Enter Product Qty: ");
            Integer productQty = scanner.nextInt();

            walletService.addProductToCart(productID, productQty);


            System.out.println("Enter 1 to continue or 2 quit");
            int result = scanner.nextInt();
            scanner.nextLine();

            if(result == 1){
                status = true;
            } else if (result == 2) {
                status = false;

            }

        }
        List<Cart> myCart = walletService.getMyProductCart();
        myCart.forEach(c->System.out.println(c));

        System.out.println("Enter wallet ID: ");
        String walletId =scanner.nextLine();

        walletService.checkOutProduct(walletId);



    }
    public static void menu() throws Exception {
        RegistrationPage registrationPage = new RegistrationPage();
        HomePage homePage = new HomePage();
        ProductCatalogPage productCatalogPage = new ProductCatalogPage();

        System.out.println(registrationPage.title());
        System.out.println(registrationPage.instruction());
        registerCustomer();
        System.out.println(homePage.title());
        System.out.println(homePage.instruction());
        fundCustomer();
        System.out.println(productCatalogPage.title());
        System.out.println(productCatalogPage.instruction());
        displayAllProducts();
        addToCart();
    }



}
