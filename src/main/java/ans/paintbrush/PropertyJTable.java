
package ans.paintbrush;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class PropertyJTable extends JTable {
  private DefaultTableModel tableModel;

    public PropertyJTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Property");
        tableModel.addColumn("Value");
        this.setModel(tableModel);
    }

    public void addRow(String property, Object value){
        tableModel.addRow(new Object[]{property,value});
        
        if(value instanceof String[]){
            setRowHeight(tableModel.getRowCount()-1, 30);
        }
        if(value instanceof MiniMaxInt){
            setRowHeight(tableModel.getRowCount()-1, 30);
        }
    }
    @Override
    public TableCellEditor getCellEditor(int row, int column) {        
        Object value = super.getValueAt(row, column);
        System.out.println(value);
        System.out.println(value.getClass().getTypeName());
        System.out.println("aaa " + (value instanceof String[]));
        
        if(value instanceof MiniMaxInt){
            return new JSpinnerCellEditor((MiniMaxInt)value);
        }
        if(value instanceof Color){
            return new JColorCellEditor((Color)value);
        }
        if(value instanceof String[]){                     
            return new JComboBoxCellEditor((String[])value);
        }
        if (value instanceof Boolean) {
            return getDefaultEditor(Boolean.class);
        }
        if (value instanceof Date) {
            return getDefaultEditor(Date.class);
        }
        if (value instanceof Integer) {
            return getDefaultEditor(Integer.class);
        }
        if (value instanceof String) {
            return getDefaultEditor(String.class);
        }
        // no special case  
        return super.getCellEditor(row, column);
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {

        Object value = super.getValueAt(row, column);
        if(value instanceof MiniMaxInt){
            return new JSpinnerCellRenderer(((MiniMaxInt)value).initVal);
        }
        if(value instanceof Color){
            return new JColorCellRenderer();
        }
        if(value instanceof String[]){
            JComboBox jComboBox = new JComboBox((String[])value);            
            return new JComboBoxCellRenderer(jComboBox);
        }
        
        if (value instanceof Boolean) {
            return getDefaultRenderer(Boolean.class);
        }
        if (value instanceof Date) {
            return getDefaultRenderer(Date.class);
        }
        if (value instanceof Integer) {
            return getDefaultRenderer(Integer.class);
        }

        if (value instanceof String) {
            return getDefaultRenderer(String.class);
        }
        // no special case  
        return super.getCellRenderer(row, column);
    }
    
    
    public class JSpinnerCellEditor extends AbstractCellEditor implements TableCellEditor, ChangeListener{
        private MiniMaxInt val;
        private JSpinner spinner;
        
        
        public JSpinnerCellEditor(MiniMaxInt val){
            this.val = val;
            SpinnerModel model =
            new SpinnerNumberModel(val.initVal, //initial value
                                    val.min, //min
                                    val.max, //max
                                    val.step);                //step
            spinner = new JSpinner(model);
            spinner.addChangeListener(this);
        }
        @Override
        public Object getCellEditorValue() {
            return val;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return spinner;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            this.val.initVal = (Integer)((JSpinner)e.getSource()).getValue();
        }
        
    }
    public class JSpinnerCellRenderer implements TableCellRenderer{
        private int val ;
        public JSpinnerCellRenderer(int val){
            this.val = val;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new JTextField(val +"");
        }
        
    }
    
    public class JColorCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener{
        private Color selectedColor = Color.red;
        public JColorCellEditor(Color c){
            this.selectedColor = c;
        }
        @Override
        public Object getCellEditorValue() {
            return selectedColor;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton colorButton = new JButton();
            colorButton.addActionListener(this);
            if(value instanceof Color)
                colorButton.setBackground((Color)value);
            return colorButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color color=JColorChooser.showDialog(null,"Select a color",selectedColor);  
            this.selectedColor = color;  
            ((JButton)e.getSource()).setBackground(color);
        }        
    }
    public class JColorCellRenderer implements TableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton colorButton = new JButton();
            if(value instanceof Color)
                colorButton.setBackground((Color)value);
            return colorButton;
        }
        
    }
    public class JComboBoxCellEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener {
        private String selectedValue;
        private String[] values;
    public JComboBoxCellEditor(String []values) {
        this.values = values;
    }
     
    @Override
    public Object getCellEditorValue() {
        return this.selectedValue;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (value instanceof String) {
            this.selectedValue = (String)value;
        }
         
        JComboBox comboValues = new JComboBox<>();
         
        for (String s : values) {
            comboValues.addItem(s);
        }
         
        comboValues.setSelectedItem(this.selectedValue);
         
        if (isSelected) {
            comboValues.setBackground(table.getSelectionBackground());
        } else {
            comboValues.setBackground(table.getSelectionForeground());
        }
         
        return comboValues;
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
        JComboBox comboValues = (JComboBox) event.getSource();
        this.selectedValue = (String) comboValues.getSelectedItem();
    }
 
}
    class JComboBoxCellRenderer implements TableCellRenderer {
        JComboBox combo;
        public JComboBoxCellRenderer(JComboBox comboBox) {
            this.combo = new JComboBox();
            for (int i=0; i<comboBox.getItemCount(); i++){
                combo.addItem(comboBox.getItemAt(i));
            }
        }
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            combo.setSelectedItem(value);
            return combo;
        }
    }
    
    
    public static class MiniMaxInt{
        int min;
        int max;
        int initVal;
        int step;
        public MiniMaxInt(int initVal,int min, int max, int step){
            this.min= min;
            this.max = max;
            this.initVal = initVal;
            this.step = step;
        }
    }
}
