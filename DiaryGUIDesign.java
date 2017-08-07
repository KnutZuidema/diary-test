import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class DiaryGUIDesign extends JFrame implements ActionListener{
    private JPanel panelNewEntryMain;
    private JTextArea textEntry;
    private JTextField textTitle;
    private JButton buttonCancel;
    private JButton buttonDeleteText;
    private JButton buttonSave;
    private JScrollPane scrollText;
    private JPanel panelButtons;
    private Entry entry;
    private JPanel panelCards;
    private CardLayout layoutCards;
    private Diary diary = new Diary();
    private JList<Entry> listFiles;
    private DefaultListModel<Entry> modelList;
    private JButton buttonNew;
    private JButton buttonEdit;
    private JButton buttonRead;
    private JButton buttonExit;
    private JButton buttonDeleteFile;
    private JPanel panelMain;
    private JPanel panelButtons2;
    private FileInputStream fis;
    private ObjectInputStream input;

    public static void main(String[] args) {
        DiaryGUIDesign dgd = new DiaryGUIDesign("Diary");
    }

    private DiaryGUIDesign(String title){
        super(title);
        layoutCards = new CardLayout();
        panelCards = new JPanel(layoutCards);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch (Exception ignored){}
        setupMainUI();
		setupNewEntryUI();
        panelMain.setBackground(Color.gray);
        panelNewEntryMain.setBackground(Color.gray);
        scrollText.setBackground(Color.gray);
        panelButtons.setBackground(Color.gray);
        panelButtons2.setBackground(Color.gray);
        listFiles.setBackground(Color.lightGray);
        this.setContentPane(panelCards);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }


    private void fillList() {
        File path = Paths.get("").resolve("entries").toFile();
        File[] files = path.listFiles();
        modelList.clear();

        if (files != null) {
            for (File file : files) {
                try {
                    fis = new FileInputStream(file);
                    input = new ObjectInputStream(fis);
                    Entry entry = (Entry) input.readObject();
                    modelList.addElement(entry);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        fis.close();
                        input.close();
                    } catch (Exception ignored) {}
                }
            }
        }
    }

    private void setupMainUI() {

        panelMain = new JPanel();
        panelMain.setLayout(new GridBagLayout());
        panelCards.add(panelMain);
        layoutCards.addLayoutComponent(panelMain, "main");

        modelList = new DefaultListModel<>();
        listFiles = new JList<>(modelList);
        fillList();
        listFiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 300;
        gbc.ipady = 500;
        panelMain.add(listFiles, gbc);


        panelButtons2 = new JPanel();
        panelButtons2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 100;
        panelMain.add(panelButtons2, gbc);

        buttonNew = new JButton();
        buttonNew.setText("New Entry");
        buttonNew.setMnemonic(KeyEvent.VK_N);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButtons2.add(buttonNew, gbc);

        buttonEdit = new JButton();
        buttonEdit.setText("Edit Entry");
        buttonEdit.setMnemonic(KeyEvent.VK_E);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButtons2.add(buttonEdit, gbc);

        buttonRead = new JButton();
        buttonRead.setText("Read Entry");
        buttonRead.setMnemonic(KeyEvent.VK_R);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButtons2.add(buttonRead, gbc);

        buttonExit = new JButton();
        buttonExit.setText("Exit");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButtons2.add(buttonExit, gbc);

        buttonDeleteFile = new JButton();
        buttonDeleteFile.setText("Delete Entry");
        buttonDeleteFile.setMnemonic(KeyEvent.VK_D);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButtons2.add(buttonDeleteFile, gbc);


        final JPanel spacer0 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelButtons2.add(spacer0, gbc);


        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelButtons2.add(spacer1, gbc);


        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelButtons2.add(spacer2, gbc);


        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        panelMain.add(spacer3, gbc);


        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        panelMain.add(spacer4, gbc);

        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panelButtons2.add(spacer5, gbc);

        spacer0.setBackground(Color.gray);
        spacer1.setBackground(Color.gray);
        spacer2.setBackground(Color.gray);
        spacer3.setBackground(Color.gray);
        spacer4.setBackground(Color.gray);
        spacer5.setBackground(Color.gray);

        buttonEdit.addActionListener(this);
        buttonNew.addActionListener(this);
        buttonRead.addActionListener(this);
        buttonExit.addActionListener(this);
        buttonDeleteFile.addActionListener(this);
    }

    private void setupNewEntryUI() {

        panelNewEntryMain = new JPanel();
        panelNewEntryMain.setLayout(new GridBagLayout());
        panelCards.add(panelNewEntryMain);
        layoutCards.addLayoutComponent(panelNewEntryMain, "newEntry");


        scrollText = new JScrollPane();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx = 500;
        gbc.ipady = 500;
        panelNewEntryMain.add(scrollText, gbc);

        textEntry = new JTextArea();
        textEntry.setLineWrap(true);
        textEntry.setText("");
        textEntry.setToolTipText("Type here...");
        scrollText.setViewportView(textEntry);


        panelButtons = new JPanel();
        panelButtons.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panelNewEntryMain.add(panelButtons, gbc);

        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        buttonCancel.setMnemonic(KeyEvent.VK_C);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panelButtons.add(buttonCancel, gbc);

        buttonDeleteText = new JButton();
        buttonDeleteText.setText("Delete All");
        buttonDeleteText.setMnemonic(KeyEvent.VK_D);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        panelButtons.add(buttonDeleteText, gbc);

        buttonSave = new JButton();
        buttonSave.setText("Save");
        buttonSave.setMnemonic(KeyEvent.VK_S);
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        panelButtons.add(buttonSave, gbc);

        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButtons.add(spacer1, gbc);

        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelButtons.add(spacer2, gbc);


        textTitle = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        panelNewEntryMain.add(textTitle, gbc);


        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.ipady = 5;
        panelNewEntryMain.add(spacer3, gbc);

        spacer1.setBackground(Color.gray);
        spacer2.setBackground(Color.gray);
        spacer3.setBackground(Color.gray);

        buttonCancel.addActionListener(this);
        buttonDeleteText.addActionListener(this);
        buttonSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listFiles.updateUI();
        if(e.getSource().equals(buttonCancel)){
            layoutCards.show(panelCards, "main");
            textEntry.setEditable(true);
            textTitle.setEditable(true);
        } else if(e.getSource().equals(buttonDeleteText)){
            if(textEntry.isEditable()) {
                textEntry.setText("");
            }
        } else if(e.getSource().equals(buttonSave)){
            String content = textEntry.getText();
            String title = textTitle.getText();
            entry.setContent(content);
            entry.setTitle(title);
            entry.writeToFile();
            fillList();
            diary.addEntry(entry);
            textEntry.setEditable(true);
            textTitle.setEditable(true);
            layoutCards.show(panelCards, "main");
        } else if(e.getSource().equals(buttonNew)){
            entry = new Entry();
            textEntry.setText("");
            layoutCards.show(panelCards, "newEntry");
        } else if(e.getSource().equals(buttonEdit)){

            if(listFiles.getSelectedValue() != null) {
                entry = listFiles.getSelectedValue();
                String text = entry.getContent();
                String title = entry.getTitle();
                textTitle.setText(title);
                textEntry.setText(text);
                layoutCards.show(panelCards, "newEntry");
            }else{
                JOptionPane.showMessageDialog(panelCards, "Select a file to edit first");
            }
        } else if(e.getSource().equals(buttonRead)) {
            if (listFiles.getSelectedValue() != null) {
                entry = listFiles.getSelectedValue();
                String text = entry.getContent();
                String title = entry.getTitle();
                textTitle.setText(title);
                textTitle.setEditable(false);
                textEntry.setText(text);
                textEntry.setEditable(false);
                layoutCards.show(panelCards, "newEntry");
            } else {
                JOptionPane.showMessageDialog(panelCards, "Select a file to read first");
            }
        }else if(e.getSource().equals(buttonDeleteFile)){
            if (listFiles.getSelectedValue() != null) {
                if(JOptionPane.showConfirmDialog(panelCards, "Do you really want to delete this entry?") == 0) {
                    entry = listFiles.getSelectedValue();
                    try {
                        Files.delete(entry.getFile().toPath());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    fillList();
                }
            } else {
                JOptionPane.showMessageDialog(panelCards, "Select a file to delete first");
            }
        }else if(e.getSource().equals(buttonExit)){
            System.exit(0);
        }
    }
}