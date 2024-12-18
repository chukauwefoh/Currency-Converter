package com.example.currencyconversion;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import java.text.DecimalFormat;




public class CurrencyConverterApplication extends Application implements Initializable {


    @FXML
    private Button btnConvert;

    @FXML
    private Button btnSwap;

    @FXML
    private Label lblCurrencyFrm;

    @FXML
    private Label lblCurrencyTo;

    @FXML
    private Label lblResult;

    @FXML
    private Label lblCode;

    @FXML
    private Label lblCodeR;

    @FXML
    private TextField txtUserAmount;

    @FXML
    private ChoiceBox<CurrencyItem> choiceFrom;

    @FXML
    private ChoiceBox<CurrencyItem> choiceTo;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblRate;

    private String[] currencyCodes = {
            "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
            "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL",
            "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP", "CNY",
            "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP",
            "ERN", "ETB", "EUR", "FJD", "FKP", "FOK", "GBP", "GEL", "GGP", "GHS",
            "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF",
            "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JEP", "JMD", "JOD",
            "JPY", "KES", "KGS", "KHR", "KID", "KMF", "KRW", "KWD", "KYD", "KZT",
            "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD",
            "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN",
            "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK",
            "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR",
            "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS", "SRD",
            "SSP", "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY",
            "TTD", "TVD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VES",
            "VND", "VUV", "WST", "XAF", "XCD", "XDR", "XOF", "XPF", "YER", "ZAR",
            "ZMW", "ZWL"
    };

    private String[] currencyNames = {
            "United Arab Emirates Dirham", "Afghan Afghani", "Albanian Lek", "Armenian Dram",
            "Netherlands Antillean Guilder", "Angolan Kwanza", "Argentine Peso", "Australian Dollar",
            "Aruban Florin", "Azerbaijani Manat", "Bosnia and Herzegovina Convertible Mark",
            "Barbadian Dollar", "Bangladeshi Taka", "Bulgarian Lev", "Bahraini Dinar",
            "Burundian Franc", "Bermudian Dollar", "Brunei Dollar", "Bolivian Boliviano",
            "Brazilian Real", "Bahamian Dollar", "Bhutanese Ngultrum", "Botswana Pula",
            "Belarusian Ruble", "Belize Dollar", "Canadian Dollar", "Congolese Franc",
            "Swiss Franc", "Chilean Peso", "Chinese Yuan", "Colombian Peso",
            "Costa Rican Colón", "Cuban Peso", "Cape Verdean Escudo", "Czech Koruna",
            "Djiboutian Franc", "Danish Krone", "Dominican Peso", "Algerian Dinar",
            "Egyptian Pound", "Eritrean Nakfa", "Ethiopian Birr", "Euro",
            "Fijian Dollar", "Falkland Islands Pound", "Faroese Króna", "British Pound Sterling",
            "Georgian Lari", "Guernsey Pound", "Ghanaian Cedi", "Gibraltar Pound",
            "Gambian Dalasi", "Guinean Franc", "Guatemalan Quetzal", "Guyanese Dollar",
            "Hong Kong Dollar", "Honduran Lempira", "Croatian Kuna", "Haitian Gourde",
            "Hungarian Forint", "Indonesian Rupiah", "Israeli New Shekel", "Isle of Man Pound",
            "Indian Rupee", "Iraqi Dinar", "Iranian Rial", "Icelandic Króna",
            "Jersey Pound", "Jamaican Dollar", "Jordanian Dinar", "Japanese Yen",
            "Kenyan Shilling", "Kyrgyzstani Som", "Cambodian Riel", "Kiribati Dollar",
            "Comorian Franc", "South Korean Won", "Kuwaiti Dinar", "Cayman Islands Dollar",
            "Kazakhstani Tenge", "Laotian Kip", "Lebanese Pound", "Sri Lankan Rupee",
            "Liberian Dollar", "Lesotho Loti", "Libyan Dinar", "Moroccan Dirham",
            "Moldovan Leu", "Malagasy Ariary", "Macedonian Denar", "Myanma Kyat",
            "Mongolian Tugrik", "Macanese Pataca", "Mauritanian Ouguiya", "Mauritian Rupee",
            "Maldivian Rufiyaa", "Malawian Kwacha", "Mexican Peso", "Malaysian Ringgit",
            "Mozambican Metical", "Namibian Dollar", "Nigerian Naira", "Nicaraguan Córdoba",
            "Norwegian Krone", "Nepalese Rupee", "New Zealand Dollar", "Omani Rial",
            "Panamanian Balboa", "Peruvian Nuevo Sol", "Papua New Guinean Kina", "Philippine Peso",
            "Pakistani Rupee", "Polish Zloty", "Paraguayan Guarani", "Qatari Rial",
            "Romanian Leu", "Serbian Dinar", "Russian Ruble", "Rwandan Franc",
            "Saudi Riyal", "Solomon Islands Dollar", "Seychellois Rupee", "Sudanese Pound",
            "Swedish Krona", "Singapore Dollar", "Saint Helena Pound", "Sierra Leonean Leone",
            "Sierra Leonean Leone", "Somali Shilling", "Surinamese Dollar", "South Sudanese Pound",
            "São Tomé and Príncipe Dobra", "Syrian Pound", "Swazi Lilangeni", "Thai Baht",
            "Tajikistani Somoni", "Turkmenistani Manat", "Tunisian Dinar", "Tongan Paʻanga",
            "Turkish Lira", "Trinidad and Tobago Dollar", "Tuvaluan Dollar", "New Taiwan Dollar",
            "Tanzanian Shilling", "Ukrainian Hryvnia", "Ugandan Shilling", "United States Dollar",
            "Uruguayan Peso", "Uzbekistani Som", "Venezuelan Bolívar", "Vietnamese Dong",
            "Vanuatu Vatu", "Samoan Tala", "CFA Franc BEAC", "East Caribbean Dollar",
            "Special Drawing Rights", "CFA Franc BCEAO", "CFP Franc", "Yemeni Rial",
            "South African Rand", "Zambian Kwacha", "Zimbabwean Dollar"
    };




    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CurrencyConverterApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();

    }

    @Override @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<CurrencyItem> currencies = new ArrayList<>();
        // Initialize ChoiceBoxes
        for(int i = 0; i < currencyCodes.length; i++){
            CurrencyItem currency = new CurrencyItem(currencyCodes[i],currencyNames[i] );
            currencies.add(currency);

        }

        choiceFrom.getItems().addAll(currencies);
        choiceTo.getItems().addAll(currencies);
        //choiceFrom.setOnAction(this::getChoice);
        //choiceTo.setOnAction(this::getChoice);

        choiceFrom.setOnAction(event -> handleChoiceSelectionFrom());
        choiceTo.setOnAction(event -> handleChoiceSelectionTo());

        btnSwap.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                CurrencyItem tempVal = choiceTo.getValue();
                choiceTo.setValue(choiceFrom.getValue());
                choiceFrom.setValue(tempVal);
                btnConvert.fire();



            }
        });


        btnConvert.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * event handler for Total button
             */
            @Override
            public void handle(ActionEvent actionEvent) {

                double usrAmount = Double.parseDouble(txtUserAmount.getText());
                User user = new User(choiceFrom.getValue().getCode(), choiceTo.getValue().getCode(),usrAmount);
                Currency currency = new Currency();

                double rate = 0;
                String date;
                try {
                    rate = currency.getRate(user.getUserFromCurrency(), user.getUserToCurrency());
                    date = currency.getDate();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println("Exchange rate from " + fromCurrency + " to " + toCurrency + ": " + rate);



                double conversion = user.getAmount() * rate;


                System.out.println(rate);
                System.out.println(conversion);

                DecimalFormat decimalFormat = new DecimalFormat("#.##########"); // Adjust the pattern as needed

                // Format the number
                String formattedRate = decimalFormat.format(rate);


                lblResult.setText(String.format("%,.2f",conversion));
                lblRate.setText(formattedRate);

                lblDate.setText(date.replaceFirst(",",""));


            }
        });

    }

    private void handleChoiceSelectionFrom() {
        CurrencyItem selectedFrom = choiceFrom.getValue();


        // Implement logic to handle the selection change
        if (selectedFrom != null) {
            lblCode.setText(selectedFrom.getCode());

            lblCurrencyFrm.setText(selectedFrom.getName());

        }


    }
    private void handleChoiceSelectionTo() {

        CurrencyItem selectedTo = choiceTo.getValue();

        // Implement logic to handle the selection change
        if (selectedTo != null) {
            lblCodeR.setText(selectedTo.getCode());
            lblCurrencyTo.setText(selectedTo.getName());

        }
    }

    public static void main(String[] args) {
        launch();
    }
}
