/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://rachota.sourceforge.net/license.txt.
 *
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://rachota.sourceforge.net/license.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * The Original Software is Rachota.
 * The Initial Developer of the Original Software is Jiri Kovalsky
 * Portions created by Jiri Kovalsky are Copyright (C) 2009
 * All Rights Reserved.
 *
 * Contributor(s): Jiri Kovalsky
 * Created on September 17, 2009   9:32 PM
 * InactivityReminderDialog.java
 */

package org.cesilko.rachota.gui;

import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import org.cesilko.rachota.core.Day;
import org.cesilko.rachota.core.Plan;
import org.cesilko.rachota.core.Settings;
import org.cesilko.rachota.core.Task;
import org.cesilko.rachota.core.Translator;

/** Notification dialog reminding user about detected inactivity.
 * @author Jiri Kovalsky
 */
public class InactivityReminderDialog extends javax.swing.JDialog {

    /** DayView to be perform user's decision about inactive time.
     */
    private final DayView dayView;

    /** Creates new inactivity reminder dialog.
     * @param dayView Day view that should be informed about user's decision.
     */
    public InactivityReminderDialog(DayView dayView) {
        this.dayView = dayView;
        currentTask = dayView.getTask();
        if (currentTask == null) currentTask = dayView.getDay().getIdleTask();
        else if (!currentTask.isRunning()) currentTask = dayView.getDay().getIdleTask();
        initComponents();
        Day today = Plan.getDefault().getDay(new Date());
        if (today.getTasks().size() > 1) {
            Iterator iterator = today.getTasks().iterator();
            while(iterator.hasNext()) {
                Task task = (Task) iterator.next();
                if (task == currentTask) continue;
                if (task.isIdleTask()) continue;
                cmbOtherTask.addItem(task);
                rbOtherTask.setEnabled(true);
            }
        }
        if (currentTask.isIdleTask()) rbNothing.setVisible(false);
        String inactivityAction = (String) Settings.getDefault().getSetting("inactivityAction");
        if (inactivityAction.equals(Settings.ON_INACTIVITY_STOP)) currentTask.suspendWork();
        setLocationRelativeTo(null);
        System.setProperty("inactivityReminderOpen", "true");
    }

    /** Checks whether all controls are enabled as it should be. */
    private void checkAccess() {
        cmbOtherTask.setEnabled(rbOtherTask.isSelected());
        lblOtherTaskTime.setEnabled(rbOtherTask.isSelected());
        spHours.setEnabled(rbOtherTask.isSelected());
        lbColumn1.setEnabled(rbOtherTask.isSelected());
        spMinutes.setEnabled(rbOtherTask.isSelected());
        lbColumn2.setEnabled(rbOtherTask.isSelected());
        spSeconds.setEnabled(rbOtherTask.isSelected());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblInactivityMessage = new javax.swing.JLabel();
        lblInactivityQuestion = new javax.swing.JLabel();
        rbContinue = new javax.swing.JRadioButton();
        rbOtherTask = new javax.swing.JRadioButton();
        cmbOtherTask = new javax.swing.JComboBox();
        lblOtherTaskTime = new javax.swing.JLabel();
        spHours = new javax.swing.JSpinner();
        lbColumn1 = new javax.swing.JLabel();
        spMinutes = new javax.swing.JSpinner();
        lbColumn2 = new javax.swing.JLabel();
        spSeconds = new javax.swing.JSpinner();
        rbNothing = new javax.swing.JRadioButton();
        chbIgnore = new javax.swing.JCheckBox();
        btOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(Translator.getTranslation("INACTIVITYDIALOG.TITLE"));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblInactivityMessage.setFont(getFont());
        lblInactivityMessage.setForeground(java.awt.Color.blue);
        lblInactivityMessage.setText(Translator.getTranslation("INACTIVITYDIALOG.LBL_INACTIVITY_MESSAGE", new String[] {(String) Settings.getDefault().getSetting("inactivityTime")}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 5, 5);
        getContentPane().add(lblInactivityMessage, gridBagConstraints);

        lblInactivityQuestion.setFont(getFont());
        lblInactivityQuestion.setText(Translator.getTranslation("INACTIVITYDIALOG.LBL_INACTIVITY_QUESTION"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 20, 5);
        getContentPane().add(lblInactivityQuestion, gridBagConstraints);

        rbContinue.setFont(getFont());
        rbContinue.setMnemonic(Translator.getMnemonic("INACTIVITYDIALOG.RB_CONTINUE"));
        rbContinue.setSelected(true);
        rbContinue.setText(Translator.getTranslation("INACTIVITYDIALOG.RB_CONTINUE", new String[] {currentTask.getDescription()}));
        rbContinue.setToolTipText(Translator.getTranslation("INACTIVITYDIALOG.RB_CONTINUE_TOOLTIP"));
        rbContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbContinueActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbContinue, gridBagConstraints);

        rbOtherTask.setFont(getFont());
        rbOtherTask.setMnemonic(Translator.getMnemonic("INACTIVITYDIALOG.RB_OTHER_TASK"));
        rbOtherTask.setText(Translator.getTranslation("INACTIVITYDIALOG.RB_OTHER_TASK"));
        rbOtherTask.setToolTipText(Translator.getTranslation("INACTIVITYDIALOG.RB_OTHER_TASK_TOOLTIP"));
        rbOtherTask.setEnabled(false);
        rbOtherTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbOtherTaskActionPerformed(evt);
            }
        });
        rbOtherTask.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbOtherTaskKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbOtherTask, gridBagConstraints);

        cmbOtherTask.setFont(getFont());
        cmbOtherTask.setEnabled(false);
        cmbOtherTask.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbOtherTaskKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(cmbOtherTask, gridBagConstraints);

        lblOtherTaskTime.setFont(getFont());
        lblOtherTaskTime.setText(Translator.getTranslation("INACTIVITYDIALOG.LBL_OTHERTASK_TIME"));
        lblOtherTaskTime.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(lblOtherTaskTime, gridBagConstraints);

        spHours.setFont(getFont());
        spHours.setToolTipText(Translator.getTranslation("MOVETIMEDIALOG.HOURS_TOOLTIP"));
        spHours.setEnabled(false);
        spHours.setMinimumSize(new java.awt.Dimension(40, 20));
        spHours.setPreferredSize(new java.awt.Dimension(40, 20));
        spHours.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spHoursStateChanged(evt);
            }
        });
        spHours.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spHoursKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(spHours, gridBagConstraints);

        lbColumn1.setFont(getFont());
        lbColumn1.setText(":");
        lbColumn1.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        getContentPane().add(lbColumn1, gridBagConstraints);

        spMinutes.setFont(getFont());
        spMinutes.setToolTipText(Translator.getTranslation("MOVETIMEDIALOG.MINUTES_TOOLTIP"));
        spMinutes.setEnabled(false);
        spMinutes.setMinimumSize(new java.awt.Dimension(40, 20));
        spMinutes.setPreferredSize(new java.awt.Dimension(40, 20));
        spMinutes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spMinutesStateChanged(evt);
            }
        });
        spMinutes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spMinutesKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(spMinutes, gridBagConstraints);

        lbColumn2.setFont(getFont());
        lbColumn2.setText(":");
        lbColumn2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        getContentPane().add(lbColumn2, gridBagConstraints);

        spSeconds.setFont(getFont());
        spSeconds.setToolTipText(Translator.getTranslation("MOVETIMEDIALOG.SECONDS_TOOLTIP"));
        spSeconds.setEnabled(false);
        spSeconds.setMinimumSize(new java.awt.Dimension(40, 20));
        spSeconds.setPreferredSize(new java.awt.Dimension(40, 20));
        spSeconds.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spSecondsStateChanged(evt);
            }
        });
        spSeconds.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spSecondsKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(spSeconds, gridBagConstraints);

        rbNothing.setFont(getFont());
        rbNothing.setMnemonic(Translator.getMnemonic("INACTIVITYDIALOG.RB_NOTHING"));
        rbNothing.setText(Translator.getTranslation("INACTIVITYDIALOG.RB_NOTHING"));
        rbNothing.setToolTipText(Translator.getTranslation("INACTIVITYDIALOG.RB_NOTHING_TOOLTIP"));
        rbNothing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNothingActionPerformed(evt);
            }
        });
        rbNothing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbNothingKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(rbNothing, gridBagConstraints);

        chbIgnore.setFont(getFont());
        chbIgnore.setMnemonic(Translator.getMnemonic("INACTIVITYDIALOG.CHB_IGNORE"));
        chbIgnore.setText(Translator.getTranslation("INACTIVITYDIALOG.CHB_IGNORE"));
        chbIgnore.setToolTipText(Translator.getTranslation("INACTIVITYDIALOG.CHB_IGNORE_TOOLTIP"));
        chbIgnore.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chbIgnoreKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(chbIgnore, gridBagConstraints);

        btOK.setFont(getFont());
        btOK.setMnemonic(Translator.getMnemonic("ADJUSTTIMEDIALOG.BT_OK"));
        btOK.setText(Translator.getTranslation("ADJUSTTIMEDIALOG.BT_OK"));
        btOK.setToolTipText(Translator.getTranslation("INACTIVITYDIALOG.BT_OK_TOOLTIP"));
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btOK, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
    setVisible(false);
    System.clearProperty("inactivityReminderOpen");
    String inactivityAction = (String) Settings.getDefault().getSetting("inactivityAction");
    if (inactivityAction.equals(Settings.ON_INACTIVITY_STOP)) currentTask.startWork();
    if (chbIgnore.isSelected()) Settings.getDefault().setSetting("detectInactivity", new Boolean(false));
    if (rbNothing.isSelected()) {
        dayView.pauseTask();
    }
    if (rbOtherTask.isSelected()) {
        long duration = previousHours.intValue()*1000*60*60 + previousMinutes.intValue()*1000*60 + previousSeconds.intValue()*1000;
        Task targetTask = (Task) cmbOtherTask.getSelectedItem();
        targetTask.addDuration(duration);
        targetTask.setState(Task.STATE_STARTED);
        currentTask.addDuration(-duration);
        dayView.selectTask(targetTask);
        addPropertyChangeListener(dayView);
        firePropertyChange("time_changed", null, null);
    }
}//GEN-LAST:event_btOKActionPerformed

    private void spHoursStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spHoursStateChanged
        Integer hours = (Integer) spHours.getValue();
        int value = hours.intValue();
        if ((value < 0) || (value > 23)) spHours.setValue(previousHours);
        else checkDuration();
}//GEN-LAST:event_spHoursStateChanged

    private void spHoursKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spHoursKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btOKActionPerformed(null);
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            setVisible(false);
}//GEN-LAST:event_spHoursKeyPressed

    private void spMinutesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spMinutesStateChanged
        Integer minutes = (Integer) spMinutes.getValue();
        int value = minutes.intValue();
        if ((value < 0) || (value > 59)) spMinutes.setValue(previousMinutes);
        else checkDuration();
}//GEN-LAST:event_spMinutesStateChanged

    private void spMinutesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spMinutesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btOKActionPerformed(null);
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            setVisible(false);
}//GEN-LAST:event_spMinutesKeyPressed

    private void spSecondsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spSecondsStateChanged
        Integer seconds = (Integer) spSeconds.getValue();
        int value = seconds.intValue();
        if ((value < 0) || (value > 59)) spSeconds.setValue(previousSeconds);
        else checkDuration();
}//GEN-LAST:event_spSecondsStateChanged

    private void spSecondsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spSecondsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btOKActionPerformed(null);
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            setVisible(false);
}//GEN-LAST:event_spSecondsKeyPressed

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        Tools.recordActivity();
    }//GEN-LAST:event_formMouseEntered

    private void rbContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbContinueActionPerformed
        rbContinue.setSelected(true);
        rbOtherTask.setSelected(false);
        rbNothing.setSelected(false);
        checkAccess();
    }//GEN-LAST:event_rbContinueActionPerformed

    private void rbOtherTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbOtherTaskActionPerformed
        rbContinue.setSelected(false);
        rbOtherTask.setSelected(true);
        rbNothing.setSelected(false);
        checkAccess();
    }//GEN-LAST:event_rbOtherTaskActionPerformed

    private void rbNothingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNothingActionPerformed
        rbContinue.setSelected(false);
        rbOtherTask.setSelected(false);
        rbNothing.setSelected(true);
        checkAccess();
    }//GEN-LAST:event_rbNothingActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btOKActionPerformed(null);
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            setVisible(false);
    }//GEN-LAST:event_formKeyPressed

    private void rbOtherTaskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbOtherTaskKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btOKActionPerformed(null);
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            setVisible(false);
    }//GEN-LAST:event_rbOtherTaskKeyPressed

    private void cmbOtherTaskKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbOtherTaskKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btOKActionPerformed(null);
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            setVisible(false);
    }//GEN-LAST:event_cmbOtherTaskKeyPressed

    private void rbNothingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbNothingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btOKActionPerformed(null);
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            setVisible(false);
    }//GEN-LAST:event_rbNothingKeyPressed

    private void chbIgnoreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chbIgnoreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            btOKActionPerformed(null);
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE)
            setVisible(false);
    }//GEN-LAST:event_chbIgnoreKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btOK;
    private javax.swing.JCheckBox chbIgnore;
    private javax.swing.JComboBox cmbOtherTask;
    private javax.swing.JLabel lbColumn1;
    private javax.swing.JLabel lbColumn2;
    private javax.swing.JLabel lblInactivityMessage;
    private javax.swing.JLabel lblInactivityQuestion;
    private javax.swing.JLabel lblOtherTaskTime;
    private javax.swing.JRadioButton rbContinue;
    private javax.swing.JRadioButton rbNothing;
    private javax.swing.JRadioButton rbOtherTask;
    private javax.swing.JSpinner spHours;
    private javax.swing.JSpinner spMinutes;
    private javax.swing.JSpinner spSeconds;
    // End of variables declaration//GEN-END:variables

    /** Task whose time should be transferred to another task. */
    private Task currentTask;
    /** Last correct value of hours specified by user. */
    private Integer previousHours = new Integer(0);
    /** Last correct value of minutes specified by user. */
    private Integer previousMinutes = new Integer(0);
    /** Last correct value of seconds specified by user. */
    private Integer previousSeconds = new Integer(0);

    /** Check whether time required for the transfer is not greater than
     * duration of the source task.
     */
    private void checkDuration() {
        Integer seconds = (Integer) spSeconds.getValue();
        Integer minutes = (Integer) spMinutes.getValue();
        Integer hours = (Integer) spHours.getValue();
        long duration = seconds.intValue()*1000 + minutes.intValue()*1000*60 + hours.intValue()*1000*60*60;
        if (duration > currentTask.getDuration()) {
            spSeconds.setValue(previousSeconds);
            spMinutes.setValue(previousMinutes);
            spHours.setValue(previousHours);
        } else {
            previousSeconds = (Integer) spSeconds.getValue();
            previousMinutes = (Integer) spMinutes.getValue();
            previousHours = (Integer) spHours.getValue();
        }
    }
}