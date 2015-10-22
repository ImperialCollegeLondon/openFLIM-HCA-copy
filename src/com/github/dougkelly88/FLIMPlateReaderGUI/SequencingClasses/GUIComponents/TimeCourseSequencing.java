/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.GUIComponents;

import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.SeqAcqProps;
import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.Variable;
import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralGUIComponents.HCAFLIMPluginFrame;
import com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes.TimePoint;
import com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes.TimeCourseTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import mmcorej.CMMCore;
import org.micromanager.MMStudio;

/**
 *
 * @author Frederik
 */
public class TimeCourseSequencing extends javax.swing.JPanel {
    
    public TimeCourseTableModel tableModel_;
    JTable timeTable_;
    HCAFLIMPluginFrame parent_;
    SeqAcqProps sap_;
    Variable var_;
    CMMCore core_; 
    MMStudio gui_;
    int selectedRow_;
    ArrayList<String> init = new ArrayList<>();
    
    
    /**
     * Creates new form SpectralSequencing
     */
    public TimeCourseSequencing() {
        initComponents();
        setControlDefaults();
        init.add("A1");
        init.add("B2");
        init.add("C3");
        init.add("D4");
        init.add("E5");
    }
    
    private void setControlDefaults(){
       
        try{
            gui_ = MMStudio.getInstance();
            core_ = gui_.getCore();
        }
        catch (Exception e){
            System.out.println("Exception = " + e.getMessage());
        }
        
        sap_ = SeqAcqProps.getInstance();
       
        
//        tableModel_ = new FilterTableModel(new FilterSetup("GFP", "465/30",
//                "ND 1.0","473/561","525/30",100,sap_.getDelaysArray().get(0)));
        tableModel_ = new TimeCourseTableModel(new TimePoint(0.0, false,
                init));
        tableModel_.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

            }
        });
        timeTable_ = new JTable();
        timeTable_.setModel(tableModel_);
        timeTable_.setSurrendersFocusOnKeystroke(true);
        timeTable_.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        JScrollPane scroller = new javax.swing.JScrollPane(timeTable_);
        timeTable_.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        timeCourseSeqBasePanel.setLayout(new BorderLayout());
        timeCourseSeqBasePanel.add(scroller, BorderLayout.CENTER);
        
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete time config");
        deleteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int r = timeTable_.getSelectedRow();
                tableModel_.removeRow(r);
            }
        });
        JMenuItem addItem = new JMenuItem("Add time config");
        addItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int r = timeTable_.getSelectedRow();
                tableModel_.insertRow(r+1, new TimePoint(-1.0, false,
                        init));
            }
        });
        
        
        popupMenu.add(addItem);
        popupMenu.add(deleteItem);
    //    popupMenu.add(setDels);
        timeTable_.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                System.out.println("pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        

        
    }
    
    private void setParent(HCAFLIMPluginFrame frame){
        parent_ = frame;
    }
    
    public ArrayList<TimePoint> getTimeTable(){
        return tableModel_.getData();
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void myinitComponents() {

        timeCourseSeqBasePanel = new javax.swing.JPanel();

        timeCourseSeqBasePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout timeCourseSeqBasePanelLayout = new javax.swing.GroupLayout(timeCourseSeqBasePanel);
        timeCourseSeqBasePanel.setLayout(timeCourseSeqBasePanelLayout);
        timeCourseSeqBasePanelLayout.setHorizontalGroup(timeCourseSeqBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );
        timeCourseSeqBasePanelLayout.setVerticalGroup(timeCourseSeqBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(timeCourseSeqBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(timeCourseSeqBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timeCourseSeqBasePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        noTimePointsField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        timeStepField = new javax.swing.JFormattedTextField();
        timeUnitsCombo = new javax.swing.JComboBox();
        popTimeCourseButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        liquidDispensionWellsList = new javax.swing.JList();
        liquidDispensionWellsTableLable = new javax.swing.JLabel();
        liquidDispensionButton = new javax.swing.JButton();

        timeCourseSeqBasePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout timeCourseSeqBasePanelLayout = new javax.swing.GroupLayout(timeCourseSeqBasePanel);
        timeCourseSeqBasePanel.setLayout(timeCourseSeqBasePanelLayout);
        timeCourseSeqBasePanelLayout.setHorizontalGroup(
            timeCourseSeqBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        timeCourseSeqBasePanelLayout.setVerticalGroup(
            timeCourseSeqBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );

        jLabel1.setText("Number of time points:");

        noTimePointsField.setText("5");

        jLabel2.setText("Time step:");

        timeStepField.setText("60");

        timeUnitsCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seconds", "Minutes", "Hours" }));
        timeUnitsCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeUnitsComboActionPerformed(evt);
            }
        });

        popTimeCourseButton.setText("Populate time course");
        popTimeCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popTimeCourseButtonActionPerformed(evt);
            }
        });

        liquidDispensionWellsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(liquidDispensionWellsList);

        liquidDispensionWellsTableLable.setText("Liquid dispension well(s):");

        liquidDispensionButton.setText("Update");
        liquidDispensionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liquidDispensionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeCourseSeqBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noTimePointsField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timeStepField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(timeUnitsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addComponent(popTimeCourseButton)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(liquidDispensionWellsTableLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(liquidDispensionButton)
                                .addGap(24, 24, 24)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeCourseSeqBasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(liquidDispensionWellsTableLable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(liquidDispensionButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(noTimePointsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(timeStepField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeUnitsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(popTimeCourseButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
/*
    private void myinitComponents() {
        timeCourseSeqBasePanel = new javax.swing.JPanel();

        timeCourseSeqBasePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout timeCourseSeqBasePanelLayout = new javax.swing.GroupLayout(timeCourseSeqBasePanel);
        timeCourseSeqBasePanel.setLayout(timeCourseSeqBasePanelLayout);
        timeCourseSeqBasePanelLayout.setHorizontalGroup(timeCourseSeqBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );
        timeCourseSeqBasePanelLayout.setVerticalGroup(timeCourseSeqBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(timeCourseSeqBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(timeCourseSeqBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }*/
    private void timeUnitsComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeUnitsComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeUnitsComboActionPerformed

    private void popTimeCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popTimeCourseButtonActionPerformed
        
        int noTimePoints = Integer.parseInt(noTimePointsField.getText());
        double tStepSecs = Double.parseDouble(timeStepField.getText());
        String units = (String) timeUnitsCombo.getSelectedItem();
        
        if (units == "Minutes")
            tStepSecs = tStepSecs * 60;
        else if (units == "Hours")
            tStepSecs = tStepSecs * 60 * 60;
        
        tableModel_.clearAllData();
        
        for (int ind = 0; ind < noTimePoints; ind++){
            tableModel_.addRow(new TimePoint(ind * tStepSecs, false, init));
        }
    }//GEN-LAST:event_popTimeCourseButtonActionPerformed

    private void liquidDispensionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liquidDispensionButtonActionPerformed
    liquidDispensionWellsList.getComponentCount();
    liquidDispensionWellsList.getComponents();
    System.out.println(liquidDispensionWellsList.getComponents());
    System.out.println(liquidDispensionWellsList.getComponentCount());
    }//GEN-LAST:event_liquidDispensionButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton liquidDispensionButton;
    private javax.swing.JList liquidDispensionWellsList;
    private javax.swing.JLabel liquidDispensionWellsTableLable;
    private javax.swing.JFormattedTextField noTimePointsField;
    private javax.swing.JButton popTimeCourseButton;
    private javax.swing.JPanel timeCourseSeqBasePanel;
    private javax.swing.JFormattedTextField timeStepField;
    private javax.swing.JComboBox timeUnitsCombo;
    // End of variables declaration//GEN-END:variables

}
