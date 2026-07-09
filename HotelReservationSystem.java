import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelReservationSystem extends JFrame {
    // Rooms data (Category, Price, Availability)
    private String[] roomTypes = {"Standard Room - ₹1,500/night", "Deluxe Room - ₹3,000/night", "Luxury Suite - ₹6,000/night"};
    private int[] roomPrices = {1500, 3000, 6000};
    
    // GUI Components
    private JComboBox<String> roomComboBox;
    private JTextField nameField, daysField;
    private JTextArea receiptArea;
    private JButton bookButton, clearButton;

    public HotelReservationSystem() {
        // Window Setup
        setTitle("CodeAlpha - Hotel Reservation System");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        JLabel titleLabel = new JLabel("Grand Horizon Hotel Booking");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Input Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 1: Guest Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Guest Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        formPanel.add(nameField, gbc);

        // Row 2: Select Room
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Select Room Category:"), gbc);
        gbc.gridx = 1;
        roomComboBox = new JComboBox<>(roomTypes);
        formPanel.add(roomComboBox, gbc);

        // Row 3: Number of Days
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Number of Days:"), gbc);
        gbc.gridx = 1;
        daysField = new JTextField(5);
        formPanel.add(daysField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Lower Panel (Buttons & Receipt)
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        // Action Buttons
        JPanel buttonPanel = new JPanel();
        bookButton = new JButton("Confirm Booking & Pay");
        bookButton.setBackground(new Color(46, 204, 113));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFont(new Font("Arial", Font.BOLD, 14));

        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(231, 76, 60));
        clearButton.setForeground(Color.WHITE);

        buttonPanel.add(bookButton);
        buttonPanel.add(clearButton);
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        // Receipt Area
        receiptArea = new JTextArea(10, 30);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        receiptArea.setBorder(BorderFactory.createTitledBorder("Booking Receipt & Payment Status"));
        JScrollPane scrollPane = new JScrollPane(receiptArea);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processBooking();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    private void processBooking() {
        String guestName = nameField.getText().trim();
        String daysStr = daysField.getText().trim();

        // Validation
        if (guestName.isEmpty() || daysStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int days = Integer.parseInt(daysStr);
            if (days <= 0) {
                JOptionPane.showMessageDialog(this, "Number of days must be greater than 0!", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int selectedIndex = roomComboBox.getSelectedIndex();
            int pricePerNight = roomPrices[selectedIndex];
            int totalBill = pricePerNight * days;

            // Payment Simulation & Receipt Generation
            receiptArea.setText("");
            receiptArea.append("=========================================\n");
            receiptArea.append("         GRAND HORIZON HOTEL             \n");
            receiptArea.append("=========================================\n");
            receiptArea.append(" Guest Name      : " + guestName + "\n");
            receiptArea.append(" Room Category   : " + roomComboBox.getSelectedItem().toString().split(" - ")[0] + "\n");
            receiptArea.append(" Stay Duration   : " + days + " Night(s)\n");
            receiptArea.append(" Price per Night : ₹" + pricePerNight + "\n");
            receiptArea.append("-----------------------------------------\n");
            receiptArea.append(" TOTAL BILL      : ₹" + totalBill + "\n");
            receiptArea.append("=========================================\n");
            receiptArea.append(" PAYMENT STATUS  : SUCCESSFUL (Simulated)\n");
            receiptArea.append(" Transaction ID  : GHH" + (int)(Math.random() * 1000000) + "\n");
            receiptArea.append(" Thank you for booking with us!\n");
            receiptArea.append("=========================================\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for days!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        daysField.setText("");
        roomComboBox.setSelectedIndex(0);
        receiptArea.setText("");
    }

    public static void main(String[] args) {
        // Run the GUI framework
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HotelReservationSystem().setVisible(true);
            }
        });
    }
}