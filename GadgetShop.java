import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class GadgetShop extends JFrame implements ActionListener {

    private ArrayList<Gadget> gadgets;

    private JTextField tfModel;
    private JTextField tfPrice;
    private JTextField tfWeight;
    private JTextField tfSize;
    private JTextField tfCredit;
    private JTextField tfMemory;
    private JTextField tfPhoneNumber;
    private JTextField tfDuration;
    private JTextField tfDownloadSize;
    private JTextField tfDisplayNumber;

    private JTextArea taOutput;

    private JButton btnAddMobile;
    private JButton btnAddMP3;
    private JButton btnClear;
    private JButton btnDisplayAll;
    private JButton btnMakeCall;
    private JButton btnAddCredit;
    private JButton btnDownloadMusic;
    private JButton btnDeleteMusic;
    private JButton btnClearOutput;

    public GadgetShop() {
        gadgets = new ArrayList<>();
        buildUI();
    }

    private void buildUI() {
        setTitle("Gadget Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 680);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(12, 14, 12, 14));
        root.setBackground(new Color(245, 247, 250));
        setContentPane(root);

        JLabel title = new JLabel("Gadget Shop", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(30, 60, 120));
        title.setBorder(new EmptyBorder(0, 0, 6, 0));
        root.add(title, BorderLayout.NORTH);

        JPanel centre = new JPanel(new GridLayout(1, 2, 14, 0));
        centre.setOpaque(false);
        root.add(centre, BorderLayout.CENTER);
        centre.add(buildInputPanel());
        centre.add(buildOutputPanel());

        root.add(buildButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 195, 220)),
                "Gadget Details", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 13), new Color(30, 60, 120)));

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 8, 5, 8);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;

        String[] labels = {
            "Model:", "Price (£):", "Weight (g):", "Size:",
            "Initial Credit (min):", "Available Memory (MB):",
            "Phone Number:", "Duration (min):",
            "Download Size (MB):", "Display Number:"
        };

        tfModel         = new JTextField(18);
        tfPrice         = new JTextField(18);
        tfWeight        = new JTextField(18);
        tfSize          = new JTextField(18);
        tfCredit        = new JTextField(18);
        tfMemory        = new JTextField(18);
        tfPhoneNumber   = new JTextField(18);
        tfDuration      = new JTextField(18);
        tfDownloadSize  = new JTextField(18);
        tfDisplayNumber = new JTextField(18);

        JTextField[] fields = {
            tfModel, tfPrice, tfWeight, tfSize, tfCredit, tfMemory,
            tfPhoneNumber, tfDuration, tfDownloadSize, tfDisplayNumber
        };

        for (int i = 0; i < labels.length; i++) {
            gc.gridx = 0; gc.gridy = i; gc.weightx = 0;
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
            panel.add(lbl, gc);
            gc.gridx = 1; gc.weightx = 1;
            fields[i].setFont(new Font("SansSerif", Font.PLAIN, 12));
            panel.add(fields[i], gc);
        }
        return panel;
    }

    private JPanel buildOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 195, 220)),
                "Output", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 13), new Color(30, 60, 120)));

        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        taOutput.setBackground(new Color(250, 252, 255));
        panel.add(new JScrollPane(taOutput), BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        panel.setOpaque(false);

        btnAddMobile     = createButton("Add Mobile",     new Color(52, 120, 246));
        btnAddMP3        = createButton("Add MP3",        new Color(52, 120, 246));
        btnClear         = createButton("Clear",          new Color(150, 150, 160));
        btnClearOutput   = createButton("Clear Output",   new Color(200, 80, 60));
        btnDisplayAll    = createButton("Display All",    new Color(34, 160, 100));
        btnMakeCall      = createButton("Make A Call",    new Color(220, 140, 30));
        btnAddCredit     = createButton("Add Credit",     new Color(220, 140, 30));
        btnDownloadMusic = createButton("Download Music", new Color(180, 60, 200));
        btnDeleteMusic   = createButton("Delete Music",   new Color(180, 60, 200));

        panel.add(btnAddMobile);
        panel.add(btnAddMP3);
        panel.add(btnDisplayAll);
        panel.add(btnMakeCall);
        panel.add(btnAddCredit);
        panel.add(btnDownloadMusic);
        panel.add(btnDeleteMusic);
        panel.add(btnClear);
        panel.add(btnClearOutput);
        return panel;
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(7, 16, 7, 16));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        return btn;
    }

    private String readModel()       { return tfModel.getText().trim(); }
    private String readSize()        { return tfSize.getText().trim(); }
    private String readPhoneNumber() { return tfPhoneNumber.getText().trim(); }

    private double readPrice() {
        try { return Double.parseDouble(tfPrice.getText().trim()); }
        catch (NumberFormatException e) { appendOutput("Error: Price must be a decimal number."); return 0.0; }
    }

    private int readWeight() {
        try { return Integer.parseInt(tfWeight.getText().trim()); }
        catch (NumberFormatException e) { appendOutput("Error: Weight must be a whole number."); return 0; }
    }

    private int readCredit() {
        try {
            int v = Integer.parseInt(tfCredit.getText().trim());
            if (v < 0) { appendOutput("Error: Credit cannot be negative."); return 0; }
            return v;
        } catch (NumberFormatException e) { appendOutput("Error: Credit must be a whole number."); return 0; }
    }

    private int readMemory() {
        try {
            int v = Integer.parseInt(tfMemory.getText().trim());
            if (v < 0) { appendOutput("Error: Memory cannot be negative."); return 0; }
            return v;
        } catch (NumberFormatException e) { appendOutput("Error: Memory must be a whole number."); return 0; }
    }

    private int readDuration() {
        try { return Integer.parseInt(tfDuration.getText().trim()); }
        catch (NumberFormatException e) { appendOutput("Error: Duration must be a whole number."); return 0; }
    }

    private int readDownloadSize() {
        try { return Integer.parseInt(tfDownloadSize.getText().trim()); }
        catch (NumberFormatException e) { appendOutput("Error: Download size must be a whole number."); return 0; }
    }

    private int getDisplayNumber() {
        int displayNumber = -1;
        try {
            int input = Integer.parseInt(tfDisplayNumber.getText().trim());
            if (input >= 0 && input < gadgets.size()) {
                displayNumber = input;
            } else {
                JOptionPane.showMessageDialog(this,
                        "Display number " + input + " is out of range.\n"
                        + "Please enter a value between 0 and " + (gadgets.size() - 1) + ".",
                        "Out of Range", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "The display number must be a whole number (integer).",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        return displayNumber;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if      (src == btnAddMobile)     addMobile();
        else if (src == btnAddMP3)        addMP3();
        else if (src == btnClear)         clearFields();
        else if (src == btnClearOutput)   clearOutput();
        else if (src == btnDisplayAll)    displayAll();
        else if (src == btnMakeCall)      makeCall();
        else if (src == btnAddCredit)     addCredit();
        else if (src == btnDownloadMusic) downloadMusic();
        else if (src == btnDeleteMusic)   deleteMusic();
    }

    private void addMobile() {
        Mobile mobile = new Mobile(readModel(), readPrice(), readWeight(), readSize(), readCredit());
        gadgets.add(mobile);
        appendOutput("Mobile added at index " + (gadgets.size() - 1) + ":");
        captureDisplay(mobile);
        appendOutput("─────────────────────────");
    }

    private void addMP3() {
        MP3 mp3 = new MP3(readModel(), readPrice(), readWeight(), readSize(), readMemory());
        gadgets.add(mp3);
        appendOutput("MP3 added at index " + (gadgets.size() - 1) + ":");
        captureDisplay(mp3);
        appendOutput("─────────────────────────");
    }

    private void clearFields() {
        tfModel.setText(""); tfPrice.setText(""); tfWeight.setText("");
        tfSize.setText(""); tfCredit.setText(""); tfMemory.setText("");
        tfPhoneNumber.setText(""); tfDuration.setText("");
        tfDownloadSize.setText(""); tfDisplayNumber.setText("");
        // Output is preserved — use "Clear Output" button to clear it
    }

    private void clearOutput() {
        taOutput.setText("");
    }

    private void displayAll() {
        if (gadgets.isEmpty()) { appendOutput("No gadgets in the shop yet."); return; }
        appendOutput("=== All Gadgets ===");
        for (int i = 0; i < gadgets.size(); i++) {
            appendOutput("Gadget #" + i + ":");
            captureDisplay(gadgets.get(i));
            appendOutput("─────────────────────────");
        }
    }

    private void makeCall() {
        int index = getDisplayNumber();
        if (index == -1) return;
        Gadget gadget = gadgets.get(index);
        if (!(gadget instanceof Mobile)) { appendOutput("Error: Gadget #" + index + " is not a Mobile phone."); return; }
        Mobile mobile = (Mobile) gadget;
        String phoneNumber = readPhoneNumber(); int duration = readDuration();
        redirectAndCall(() -> mobile.makeCall(phoneNumber, duration));
        appendOutput("─────────────────────────");
    }

    private void downloadMusic() {
        int index = getDisplayNumber();
        if (index == -1) return;
        Gadget gadget = gadgets.get(index);
        if (!(gadget instanceof MP3)) { appendOutput("Error: Gadget #" + index + " is not an MP3 player."); return; }
        MP3 mp3 = (MP3) gadget; int downloadSize = readDownloadSize();
        redirectAndCall(() -> mp3.downloadMusic(downloadSize));
        appendOutput("─────────────────────────");
    }

    private void addCredit() {
        int index = getDisplayNumber();
        if (index == -1) return;
        Gadget gadget = gadgets.get(index);
        if (!(gadget instanceof Mobile)) { appendOutput("Error: Gadget #" + index + " is not a Mobile phone."); return; }
        Mobile mobile = (Mobile) gadget; int amount = readCredit();
        redirectAndCall(() -> mobile.addCredit(amount));
        appendOutput("─────────────────────────");
    }

    private void deleteMusic() {
        int index = getDisplayNumber();
        if (index == -1) return;
        Gadget gadget = gadgets.get(index);
        if (!(gadget instanceof MP3)) { appendOutput("Error: Gadget #" + index + " is not an MP3 player."); return; }
        MP3 mp3 = (MP3) gadget; int memoryFreed = readDownloadSize();
        redirectAndCall(() -> mp3.deleteMusic(memoryFreed));
        appendOutput("─────────────────────────");
    }

    private void captureDisplay(Gadget gadget) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream old = System.out;
        System.setOut(new java.io.PrintStream(baos));
        gadget.display();
        System.out.flush();
        System.setOut(old);
        taOutput.append(baos.toString());
        if (!baos.toString().endsWith("\n")) taOutput.append("\n");
    }

    private void redirectAndCall(Runnable action) {
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream old = System.out;
        System.setOut(new java.io.PrintStream(baos));
        action.run();
        System.out.flush();
        System.setOut(old);
        taOutput.append(baos.toString());
        if (!baos.toString().endsWith("\n")) taOutput.append("\n");
    }

    private void appendOutput(String text) {
        taOutput.append(text + "\n");
        taOutput.setCaretPosition(taOutput.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GadgetShop shop = new GadgetShop();
            shop.setVisible(true);
        });
    }
}
