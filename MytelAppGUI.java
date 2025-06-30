import javax.swing.*;
import java.awt.event.*;

public class MytelAppGUI {
    MytelApp app;

    public MytelAppGUI() {
        app = new MytelApp("Rahul", "Ameerpet, Hyderabad", "8765432567");

        JFrame frame = new JFrame("Mytel Services");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel label = new JLabel("Choose a Service:");
        label.setBounds(30, 30, 200, 30);
        frame.add(label);

        String[] options = {
                "1. Recharge",
                "2. Pay Bill",
                "3. Convert Plan",
                "4. Check Balance",
                "5. Profile Details",
                "6. Type Casting Example"
        };

        JComboBox<String> serviceBox = new JComboBox<>(options);
        serviceBox.setBounds(200, 30, 250, 30);
        frame.add(serviceBox);

        JButton proceedButton = new JButton("Proceed");
        proceedButton.setBounds(180, 80, 120, 30);
        frame.add(proceedButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBounds(30, 130, 420, 200);
        frame.add(scroll);

        proceedButton.addActionListener(e -> {
            int choice = serviceBox.getSelectedIndex() + 1;
            resultArea.setText("");
            switch (choice) {
                case 1 -> rechargeUI(resultArea);
                case 2 -> payBillUI(resultArea);
                case 3 -> convertPlanUI(resultArea);
                case 4 -> checkBalanceUI(resultArea);
                case 5 -> profileUI(resultArea);
                case 6 -> {
                    app.castingExample();
                    resultArea.setText("Type casting executed (Check console).\nRounded amount: ₹300");
                }
            }
        });

        frame.setVisible(true);
    }

    private void rechargeUI(JTextArea output) {
        String num = JOptionPane.showInputDialog("Enter Prepaid Number:");
        if (num == null || num.isEmpty()) return;
        String[] plans = {"₹200, 30 days, 3GB/day", "₹300, 30 days, 6GB/day"};
        String plan = (String) JOptionPane.showInputDialog(null, "Choose Plan:",
                "Recharge", JOptionPane.QUESTION_MESSAGE, null, plans, plans[0]);
        if (plan != null) {
            output.setText("Recharge successful for number: " + num + "\nPlan: " + plan);
        }
    }

    private void payBillUI(JTextArea output) {
        String number = JOptionPane.showInputDialog("Enter Postpaid Number:");
        if (number == null || number.isEmpty()) return;
        output.setText("Your due amount: ₹600\nBill paid successfully for: " + number);
    }

    private void convertPlanUI(JTextArea output) {
        String number = JOptionPane.showInputDialog("Enter your number:");
        if (number == null || number.isEmpty()) return;
        String[] choices = {"Prepaid to Postpaid", "Postpaid to Prepaid"};
        String selected = (String) JOptionPane.showInputDialog(null, "Select conversion:",
                "Convert Plan", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        if (selected != null) {
            output.setText("Conversion request submitted for: " + number +
                    "\nType: " + selected + "\nNew SIM will be delivered soon.");
        }
    }

    private void checkBalanceUI(JTextArea output) {
        String number = JOptionPane.showInputDialog("Enter your number:");
        if (number == null || number.isEmpty()) return;
        output.setText("Balance Details:\nSMS: 30\nTalktime: Unlimited\nData: 1GB");
    }

    private void profileUI(JTextArea output) {
        app.displayProfile();
        output.setText("Name: " + app.name.toUpperCase() +
                "\nAddress: " + app.address.trim());
    }

    public static void main(String[] args) {
        new MytelAppGUI();
    }
}
