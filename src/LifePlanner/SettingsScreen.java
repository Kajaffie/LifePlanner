package LifePlanner;

import com.sun.security.auth.module.NTSystem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.DefaultComboBoxModel;
import org.ini4j.Ini;

/**
 * The screen for displaying and changing the settings of the program.
 *
 * @author Jafeth
 */
public class SettingsScreen extends javax.swing.JFrame {

    private NTSystem NTSystem;
    private MainScreen mainScreen;

    /**
     * Creates new form SettingsScreen
     *
     * @param mainScreen the mainScreen this screen belongs to.
     */
    public SettingsScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        NTSystem = new com.sun.security.auth.module.NTSystem();
        initComponents();
        try {
            setStrings();
        } catch (Exception e) {
            System.out.println("Problem while reading in Strings for the settings screen." + e);
        }
        getLanguages();
    }

    /**
     * Retrieves the language files to choose from.
     */
    private void getLanguages() {
        Filter filter = new Filter(".ini");
        File[] files = filter.finder("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\Languages");
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for (File file : files) {
            model.addElement(file);
        }

        this.languageDropDown.setModel(model);
    }

    /**
     * Sets the texts in the labels and on the buttons from the language
     * settings file.
     *
     * @throws IOException
     */
    private void setStrings() throws IOException {
        Ini ini = new Ini(new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\LifeplannerSettings.ini"));
        Ini language = new Ini(new File("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\Languages\\" + ini.get("header", "language") + ".ini"));
        titleLabel.setText(language.get("settings", "title"));
        languageLabel.setText(language.get("settings", "language"));
        okButton.setText(language.get("common", "ok"));
        cancelButton.setText(language.get("common", "cancel"));
    }

    /**
     * Changes the language the program communicates to the user with.
     *
     * @throws IOException
     */
    private void changeLanguage() throws IOException {
        File f = (File) this.languageDropDown.getSelectedItem();
        String textLine = "language = " + f.getName().replaceAll(".ini", "");
        String[] ini = openFile();
        FileWriter fileWriter = new FileWriter("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\LifeplannerSettings.ini", false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < ini.length; i++) {
            if (ini[i].contains("language")) {
                printWriter.printf("%s" + "%n", textLine);
            } else {
                printWriter.printf("%s" + "%n", ini[i]);
            }
        }
        printWriter.close();
        mainScreen.setStrings();
    }

    /**
     * Opens a file and gives back its content.
     *
     * @return a String array with the content of the opened file.
     * @throws IOException
     */
    private String[] openFile() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\LifeplannerSettings.ini");
        String[] textData;
        int numberOfLines = readLines();
        textData = new String[numberOfLines];
        try (BufferedReader textReader = new BufferedReader(fr)) {
            for (int i = 0; i < numberOfLines; i++) {
                textData[i] = textReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Problem while opening file." + e);
        }

        return textData;
    }

    /**
     * Counts the amount of lines in a file.
     *
     * @return The amount of lines in the file.
     * @throws IOException
     */
    private int readLines() throws IOException {
        FileReader fileToRead = new FileReader("C:\\Users\\" + NTSystem.getName() + "\\Documents\\LifePlanner\\Settings\\LifeplannerSettings.ini");
        int numberOfLines;
        numberOfLines = 0;
        try (BufferedReader bf = new BufferedReader(fileToRead)) {
            while (bf.readLine() != null) {
                numberOfLines++;
            }
        } catch (Exception e) {
            System.out.println("Problem while reading the amount of lines." + e);
        }

        return numberOfLines;
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        languageLabel = new javax.swing.JLabel();
        languageDropDown = new javax.swing.JComboBox();
        titleLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(languageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(languageDropDown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(languageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(languageDropDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        try {
            changeLanguage();
        } catch (Exception e) {
            System.out.println("Problem while changing languages" + e);
        }
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox languageDropDown;
    private javax.swing.JLabel languageLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
