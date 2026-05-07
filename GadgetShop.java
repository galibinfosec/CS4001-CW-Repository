import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class GadgetShop extends JFrame implements ActionListener {

    private ArrayList<Gadget> gadgets;

    // ── Text fields ──────────────────────────────────────────────────────────
    private JTextField tfModel, tfPrice, tfWeight, tfSize;
    private JTextField tfCredit, tfPhoneNumber, tfDuration;
    private JTextField tfMemory, tfDownloadSize;
    private JTextField tfDisplayNumber;

    // ── Output & status ──────────────────────────────────────────────────────
    private JTextArea taOutput;
    private JLabel    lblStatus;
    private JLabel    lblCount;

    // ── Action buttons ───────────────────────────────────────────────────────
    private JButton btnAddMobile, btnAddMP3, btnDisplayAll;
    private JButton btnMakeCall, btnAddCredit;
    private JButton btnDownloadMusic, btnDeleteMusic;
    private JButton btnClear;

    // ── View toggle buttons ──────────────────────────────────────────────────
    private JButton btnFull, btnCompact;

    // ── Palette ──────────────────────────────────────────────────────────────
    private static final Color C_NAVY   = new Color(10,  35,  90);
    private static final Color C_BLUE   = new Color(33, 100, 210);
    private static final Color C_GREEN  = new Color(22, 145,  75);
    private static final Color C_AMBER  = new Color(195, 115,  10);
    private static final Color C_VIOLET = new Color(125,  30, 175);
    private static final Color C_RED    = new Color(195,  30,  30);
    private static final Color C_BG     = new Color(232, 237, 247);
    private static final Color C_WHITE  = Color.WHITE;
    private static final Color C_STRIPE = new Color(243, 246, 252);

    // ── Window presets ───────────────────────────────────────────────────────
    private static final Dimension DIM_FULL    = new Dimension(1024, 768);
    private static final Dimension DIM_COMPACT = new Dimension(780, 580);

    // ════════════════════════════════════════════════════════════════════════
    public GadgetShop() {
        gadgets = new ArrayList<>();
        buildUI();
    }

    // ════════════════════════════════════════════════════════════════════════
    //  BUILD UI
    // ════════════════════════════════════════════════════════════════════════
    private void buildUI() {
        setTitle("Gadget Shop  \u2014  CS4001");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(DIM_FULL);
        setMinimumSize(DIM_COMPACT);
        setResizable(true);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(C_BG);
        setContentPane(root);

        root.add(buildHeader(),  BorderLayout.NORTH);
        root.add(buildCenter(),  BorderLayout.CENTER);
        root.add(buildSouth(),   BorderLayout.SOUTH);
    }

    // ── Header ───────────────────────────────────────────────────────────────
    private JPanel buildHeader() {
        JPanel p = new JPanel(new BorderLayout(0, 0));
        p.setBackground(C_NAVY);
        p.setBorder(new EmptyBorder(12, 20, 12, 20));

        // Left: title + subtitle
        JLabel title = new JLabel("  Gadget Shop");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(C_WHITE);

        JLabel sub = new JLabel("  CS4001  \u2014  Mobile Phones & MP3 Players");
        sub.setFont(new Font("SansSerif", Font.PLAIN, 11));
        sub.setForeground(new Color(155, 185, 235));

        JPanel left = new JPanel(new GridLayout(2, 1, 0, 1));
        left.setOpaque(false);
        left.add(title);
        left.add(sub);
        p.add(left, BorderLayout.WEST);

        // Right: item count + view buttons
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        right.setOpaque(false);

        lblCount = new JLabel("0 items");
        lblCount.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblCount.setForeground(new Color(155, 185, 235));

        btnFull    = headerBtn("Full Display");
        btnCompact = headerBtn("Compact");

        right.add(lblCount);
        right.add(Box.createHorizontalStrut(14));
        right.add(btnFull);
        right.add(btnCompact);
        p.add(right, BorderLayout.EAST);

        return p;
    }

    private JButton headerBtn(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 11));
        b.setForeground(C_WHITE);
        b.setBackground(new Color(40, 70, 140));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(new EmptyBorder(5, 14, 5, 14));
        b.addActionListener(this);
        return b;
    }

    // ── Center ────────────────────────────────────────────────────────────────
    private JSplitPane buildCenter() {
        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                buildInputPanel(),
                buildOutputPanel());
        split.setDividerLocation(380);
        split.setDividerSize(6);
        split.setBorder(new EmptyBorder(8, 8, 4, 8));
        split.setBackground(C_BG);
        split.setOneTouchExpandable(true);
        return split;
    }

    // ── Input panel ───────────────────────────────────────────────────────────
    private JScrollPane buildInputPanel() {
        JPanel body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBackground(C_WHITE);
        body.setBorder(new EmptyBorder(10, 14, 14, 14));

        addSection(body, "Gadget Details", C_BLUE);
        addField(body, "Model :",         tfModel  = tf());
        addField(body, "Price (\u00a3):", tfPrice  = tf());
        addField(body, "Weight (g) :",    tfWeight = tf());
        addField(body, "Size :",          tfSize   = tf());
        body.add(gap(10));

        addSection(body, "Mobile Phone", C_AMBER);
        addField(body, "Initial Credit (min) :", tfCredit      = tf());
        addField(body, "Phone Number :",          tfPhoneNumber = tf());
        addField(body, "Duration (min) :",        tfDuration    = tf());
        body.add(gap(10));

        addSection(body, "MP3 Player", C_VIOLET);
        addField(body, "Available Memory (MB) :", tfMemory       = tf());
        addField(body, "Download Size (MB) :",    tfDownloadSize = tf());
        body.add(gap(10));

        addSection(body, "Action Target", C_GREEN);
        addField(body, "Display Number :", tfDisplayNumber = tf());
        body.add(gap(6));

        JScrollPane scroll = new JScrollPane(body);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(195, 210, 230)));
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        return scroll;
    }

    // ── Output panel ──────────────────────────────────────────────────────────
    private JPanel buildOutputPanel() {
        JPanel p = new JPanel(new BorderLayout(0, 0));
        p.setBackground(C_WHITE);
        p.setBorder(BorderFactory.createLineBorder(new Color(195, 210, 230)));

        // Output header bar
        JPanel hdr = new JPanel(new BorderLayout());
        hdr.setBackground(new Color(245, 248, 255));
        hdr.setBorder(new EmptyBorder(7, 12, 7, 12));
        JLabel lbl = new JLabel("Output");
        lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        lbl.setForeground(C_NAVY);
        hdr.add(lbl, BorderLayout.WEST);
        p.add(hdr, BorderLayout.NORTH);

        taOutput = new JTextArea();
        taOutput.setEditable(false);
        taOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        taOutput.setBackground(new Color(249, 252, 255));
        taOutput.setLineWrap(true);
        taOutput.setWrapStyleWord(true);
        taOutput.setBorder(new EmptyBorder(8, 10, 8, 10));

        JScrollPane scroll = new JScrollPane(taOutput);
        scroll.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(215, 225, 240)));
        p.add(scroll, BorderLayout.CENTER);
        return p;
    }

    // ── South: buttons + status ───────────────────────────────────────────────
    private JPanel buildSouth() {
        JPanel south = new JPanel(new BorderLayout(0, 0));
        south.setBackground(C_BG);

        JPanel btnArea = new JPanel();
        btnArea.setLayout(new BoxLayout(btnArea, BoxLayout.Y_AXIS));
        btnArea.setBackground(C_BG);
        btnArea.setBorder(new EmptyBorder(4, 8, 4, 8));

        // Row 1 — Add & View
        JPanel row1 = btnRow("Add / View :");
        btnAddMobile  = actionBtn(row1, "Add Mobile",  C_BLUE);
        btnAddMP3     = actionBtn(row1, "Add MP3",     C_BLUE);
        btnDisplayAll = actionBtn(row1, "Display All", C_GREEN);
        btnArea.add(row1);
        btnArea.add(gap(4));

        // Row 2 — Mobile & MP3 actions
        JPanel row2 = btnRow("Actions :");
        btnMakeCall      = actionBtn(row2, "Make A Call",    C_AMBER);
        btnAddCredit     = actionBtn(row2, "Add Credit",     C_AMBER);
        btnDownloadMusic = actionBtn(row2, "Download Music", C_VIOLET);
        btnDeleteMusic   = actionBtn(row2, "Delete Music",   C_VIOLET);
        btnArea.add(row2);
        btnArea.add(gap(4));

        // Row 3 — Clear (full-width red)
        JPanel row3 = new JPanel(new BorderLayout(0, 0));
        row3.setBackground(C_BG);
        JLabel pad = new JLabel("              ");
        pad.setFont(new Font("SansSerif", Font.BOLD, 11));
        row3.add(pad, BorderLayout.WEST);

        btnClear = new JButton("\u2715    Clear All Fields & Output");
        btnClear.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnClear.setBackground(C_RED);
        btnClear.setForeground(C_WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);
        btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClear.setBorder(new EmptyBorder(9, 0, 9, 0));
        btnClear.addActionListener(this);
        row3.add(btnClear, BorderLayout.CENTER);
        btnArea.add(row3);

        south.add(btnArea, BorderLayout.CENTER);

        // Status bar
        lblStatus = new JLabel("  Ready");
        lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblStatus.setForeground(new Color(80, 95, 120));
        lblStatus.setBackground(new Color(215, 222, 235));
        lblStatus.setOpaque(true);
        lblStatus.setBorder(new EmptyBorder(4, 10, 4, 10));
        south.add(lblStatus, BorderLayout.SOUTH);

        return south;
    }

    // ════════════════════════════════════════════════════════════════════════
    //  UI HELPERS
    // ════════════════════════════════════════════════════════════════════════

    private void addSection(JPanel p, String text, Color accent) {
        JPanel bg = new JPanel(new BorderLayout());
        bg.setBackground(C_STRIPE);
        bg.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        bg.setAlignmentX(Component.LEFT_ALIGNMENT);
        bg.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, accent),
                new EmptyBorder(4, 8, 4, 0)));
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setForeground(accent.darker());
        bg.add(lbl);
        p.add(bg);
        p.add(gap(4));
    }

    private void addField(JPanel p, String labelText, JTextField field) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(C_WHITE);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setBorder(new EmptyBorder(2, 6, 2, 6));

        JLabel lbl = new JLabel(labelText);
        lbl.setPreferredSize(new Dimension(175, 24));
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lbl.setForeground(new Color(50, 65, 90));

        field.setFont(new Font("SansSerif", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(185, 200, 225)),
                new EmptyBorder(3, 7, 3, 7)));

        row.add(lbl,   BorderLayout.WEST);
        row.add(field, BorderLayout.CENTER);
        p.add(row);
        p.add(gap(2));
    }

    private Component gap(int h) {
        return Box.createRigidArea(new Dimension(0, h));
    }

    private JTextField tf() {
        return new JTextField();
    }

    private JPanel btnRow(String label) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 2));
        row.setBackground(C_BG);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        lbl.setForeground(new Color(95, 110, 140));
        lbl.setPreferredSize(new Dimension(78, 24));
        row.add(lbl);
        return row;
    }

    private JButton actionBtn(JPanel row, String text, Color color) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setBackground(color);
        b.setForeground(C_WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(new EmptyBorder(7, 18, 7, 18));
        b.addActionListener(this);
        row.add(b);
        return b;
    }

    private void refreshCount() {
        lblCount.setText(gadgets.size() + " item" + (gadgets.size() == 1 ? "" : "s"));
    }

    private void setStatus(String msg) {
        lblStatus.setText("  " + msg);
    }

    // ════════════════════════════════════════════════════════════════════════
    //  INPUT READERS
    // ════════════════════════════════════════════════════════════════════════

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

    /**
     * Validates the display number field.
     * Returns -1 on error; shows two distinct JOptionPane dialogs.
     */
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

    // ════════════════════════════════════════════════════════════════════════
    //  ACTION HANDLER
    // ════════════════════════════════════════════════════════════════════════

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if      (src == btnAddMobile)     addMobile();
        else if (src == btnAddMP3)        addMP3();
        else if (src == btnDisplayAll)    displayAll();
        else if (src == btnMakeCall)      makeCall();
        else if (src == btnAddCredit)     addCredit();
        else if (src == btnDownloadMusic) downloadMusic();
        else if (src == btnDeleteMusic)   deleteMusic();
        else if (src == btnClear)         clearFields();
        else if (src == btnFull)          setFullDisplay();
        else if (src == btnCompact)       setCompactDisplay();
    }

    // ════════════════════════════════════════════════════════════════════════
    //  BUTTON HANDLERS
    // ════════════════════════════════════════════════════════════════════════

    private void addMobile() {
        Mobile m = new Mobile(readModel(), readPrice(), readWeight(), readSize(), readCredit());
        gadgets.add(m);
        appendOutput("Mobile added at index " + (gadgets.size() - 1) + ":");
        captureDisplay(m);
        appendSep();
        refreshCount();
        setStatus("Mobile phone added — index " + (gadgets.size() - 1));
    }

    private void addMP3() {
        MP3 mp3 = new MP3(readModel(), readPrice(), readWeight(), readSize(), readMemory());
        gadgets.add(mp3);
        appendOutput("MP3 added at index " + (gadgets.size() - 1) + ":");
        captureDisplay(mp3);
        appendSep();
        refreshCount();
        setStatus("MP3 player added — index " + (gadgets.size() - 1));
    }

    private void clearFields() {
        for (JTextField f : new JTextField[]{
                tfModel, tfPrice, tfWeight, tfSize,
                tfCredit, tfPhoneNumber, tfDuration,
                tfMemory, tfDownloadSize, tfDisplayNumber})
            f.setText("");
        taOutput.setText("");
        setStatus("All fields and output cleared.");
    }

    private void displayAll() {
        if (gadgets.isEmpty()) {
            appendOutput("No gadgets in the shop yet.");
            setStatus("No gadgets to display.");
            return;
        }
        appendOutput("=== All Gadgets (" + gadgets.size() + " item"
                + (gadgets.size() == 1 ? "" : "s") + ") ===");
        for (int i = 0; i < gadgets.size(); i++) {
            appendOutput("Gadget #" + i + "  ["
                    + (gadgets.get(i) instanceof Mobile ? "Mobile" : "MP3") + "]:");
            captureDisplay(gadgets.get(i));
            appendSep();
        }
        setStatus("Displayed all " + gadgets.size() + " gadget(s).");
    }

    private void makeCall() {
        int idx = getDisplayNumber();
        if (idx == -1) return;
        Gadget g = gadgets.get(idx);
        if (!(g instanceof Mobile)) { appendOutput("Error: Gadget #" + idx + " is not a Mobile phone."); return; }
        String ph = readPhoneNumber(); int dur = readDuration();
        redirectAndCall(() -> ((Mobile) g).makeCall(ph, dur));
        appendSep();
        setStatus("Make A Call executed on gadget #" + idx);
    }

    private void addCredit() {
        int idx = getDisplayNumber();
        if (idx == -1) return;
        Gadget g = gadgets.get(idx);
        if (!(g instanceof Mobile)) { appendOutput("Error: Gadget #" + idx + " is not a Mobile phone."); return; }
        int amt = readCredit();
        redirectAndCall(() -> ((Mobile) g).addCredit(amt));
        appendSep();
        setStatus("Add Credit executed on gadget #" + idx);
    }

    private void downloadMusic() {
        int idx = getDisplayNumber();
        if (idx == -1) return;
        Gadget g = gadgets.get(idx);
        if (!(g instanceof MP3)) { appendOutput("Error: Gadget #" + idx + " is not an MP3 player."); return; }
        int ds = readDownloadSize();
        redirectAndCall(() -> ((MP3) g).downloadMusic(ds));
        appendSep();
        setStatus("Download Music executed on gadget #" + idx);
    }

    private void deleteMusic() {
        int idx = getDisplayNumber();
        if (idx == -1) return;
        Gadget g = gadgets.get(idx);
        if (!(g instanceof MP3)) { appendOutput("Error: Gadget #" + idx + " is not an MP3 player."); return; }
        int mf = readDownloadSize();
        redirectAndCall(() -> ((MP3) g).deleteMusic(mf));
        appendSep();
        setStatus("Delete Music executed on gadget #" + idx);
    }

    // ── View toggle handlers ──────────────────────────────────────────────────

    private void setFullDisplay() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setStatus("Full Display mode.");
    }

    private void setCompactDisplay() {
        setExtendedState(JFrame.NORMAL);
        setSize(DIM_COMPACT);
        setLocationRelativeTo(null);
        setStatus("Compact Display mode.");
    }

    // ════════════════════════════════════════════════════════════════════════
    //  UTILITIES
    // ════════════════════════════════════════════════════════════════════════

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

    private void appendSep() {
        taOutput.append("\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500"
                + "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500"
                + "\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\n");
        taOutput.setCaretPosition(taOutput.getDocument().getLength());
    }

    // ════════════════════════════════════════════════════════════════════════
    //  ENTRY POINT
    // ════════════════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GadgetShop().setVisible(true));
    }
}
