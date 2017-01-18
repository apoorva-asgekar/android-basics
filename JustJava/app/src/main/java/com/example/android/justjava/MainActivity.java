package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.android.justjava.R.string.chocolate;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox)findViewById(R.id.whipped_cream_check_box);
        boolean wantWhippedCream = whippedCream.isChecked();
        Log.i("MainACtivity.java", "Whipped cream checked? " + wantWhippedCream);
        CheckBox chocolate = (CheckBox)findViewById(R.id.chocolate_check_box);
        boolean wantChocolate = chocolate.isChecked();

        int price = calculatePrice(wantWhippedCream, wantChocolate);

        Log.i("MainACtivity.java", "Chocolate checked? " + wantChocolate);
        EditText name = (EditText) findViewById(R.id.name_edit_text);
        Log.i("MainActivity.java","Name is: " + name.getText().toString());
        String orderSummary =
                createOrderSummary(price, wantWhippedCream, wantChocolate,
                        name.getText().toString());

        String subject = getString(R.string.order_summary_email_subject, name.getText().toString());
        String[] addresses = {"amogh.asgekar@gmail.com"};
        //displayMessage(orderSummary);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * Calculates the price of the order.
     *
     * @param wantWhippedCream on coffee
     * @param wantChocolate on coffee
     * @return total price of coffee
     */
    private int calculatePrice(boolean wantWhippedCream, boolean wantChocolate) {
        int basePrice = 5;
        if(wantWhippedCream) {
            basePrice += 1;
        }
        if(wantChocolate) {
            basePrice += 2;
        }
        int price = quantity * basePrice;
        return price;
    }

    /**
     * Returns the order summary.
     *
     * @param totalPrice of order
     * @param wantWhippedCream on your order
     * @return order summary
     */
    private String createOrderSummary(int totalPrice, boolean wantWhippedCream,
                                      boolean wantChocolate, String name) {
        String orderSummary = getString(R.string.order_summary_name, name) + "\n";
        orderSummary += getString(R.string.order_summary_whipped_cream, wantWhippedCream) + "\n";
        orderSummary += getString(R.string.order_summary_chocolate, wantChocolate) + "\n";
        orderSummary += getString(R.string.order_summary_quantity, quantity) + "\n"
                + getString(R.string.order_summary_price, totalPrice) + "\n"
                + getString(R.string.thank_you);
        return orderSummary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method increments the ordered quantity.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot order more than 100 coffees!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method decrements the ordered quantity.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot order fewer than 1 coffees!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    /*private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/


}