/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obuits;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class for displaying a custom keyboard input dialog. Author: Sumitha
 */
public class clsKeyBoardView {

    /**
     * Displays a dialog with a custom keyboard panel and returns the input
     * value.
     *
     * @param title The title of the dialog.
     * @param txt_val The initial value to be displayed in the keyboard input
     * field.
     * @return The value entered in the keyboard input field, or null if
     * cancelled.
     */
    public String getKeyBoardValue(String title, String txt_val) {
        // Create a new custom keyboard panel with the initial value.
        PanKeyBoard panel = new PanKeyBoard(txt_val);

        // Create a JOptionPane with the custom keyboard panel.
        JOptionPane pane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION) {
            @Override
            public void selectInitialValue() {
                // Override to prevent initial focus selection
            }
        };

        // Retrieve the button panel containing OK and Cancel buttons.
        JPanel buttonPanel = (JPanel) pane.getComponent(1);

        // Customize the OK button.
        JButton buttonOk = (JButton) buttonPanel.getComponent(0);
        buttonOk.setText(" OK "); // Set the text of the OK button.
        buttonOk.setFont(new java.awt.Font("Tahoma", 1, 18)); // Set the font of the OK button.
        buttonOk.setPreferredSize(new Dimension(100, 50)); // Set the preferred size of the OK button.
        buttonOk.validate(); // Validate the button to apply changes.

        // Customize the Cancel button.
        JButton buttonCancel = (JButton) buttonPanel.getComponent(1);
        buttonCancel.setText("CANCEL"); // Set the text of the Cancel button.
        buttonCancel.setFont(new java.awt.Font("Tahoma", 1, 18)); // Set the font of the Cancel button.
        buttonCancel.setPreferredSize(new Dimension(120, 50)); // Set the preferred size of the Cancel button.
        buttonCancel.validate(); // Validate the button to apply changes.

        // Create and display the dialog with the custom keyboard panel.
        JDialog getKeyBrdDialog = pane.createDialog(null, title);
        getKeyBrdDialog.setAlwaysOnTop(true); // Set the dialog to always be on top.
        getKeyBrdDialog.setVisible(true); // Make the dialog visible.
        Object selectedValue = pane.getValue();
        int n = -1;

        // Get the value entered by the use
        if (selectedValue == null) {
            n = JOptionPane.CANCEL_OPTION;
        } else {
            n = Integer.parseInt(selectedValue.toString());
        }

        if (n == JOptionPane.OK_OPTION) {
            return PanKeyBoard.txtKeyBoardData.getText();
        } else {

            return null;
        }

    }
}
