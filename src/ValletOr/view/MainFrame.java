package ValletOr.view;

import ValletOr.MyTableModel;
import ValletOr.data.CompControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

import javax.swing.event.DocumentEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableStringConverter;

public class MainFrame extends JFrame {

    //Кнопки
    private JButton addButton;
    private JButton delButton;
    //таблица, скролл и её модель
    private JTable table;
    private JScrollPane jsp;
    private MyTableModel mtm;
    private TableRowSorter<MyTableModel> sorter;
    //Панели
    private JPanel controlsPanel;
    private JPanel addPanel;
    private JPanel addTextPanel;
    private JPanel addButtonPanel;
    private JPanel searchPanel;
    //Поля для текста
    private JTextField nameField;
    private JTextField empField;
    private JTextField specField;
    private JTextField searchField;
    //Выпадное меню
    private JComboBox typeBox;
    //Лейблы
    private JLabel addLabel;
    private JLabel searchLabel;
    //Меню-бар
    private JMenuBar menuBar;

    public MainFrame(){
        super("Лабораторная работа №5");

        init();

        //setPreferredSize(new Dimension(720, 480));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setJMenuBar(menuBar);

        addButtonPanel.add(typeBox);
        addButtonPanel.add(addButton);
        addTextPanel.add(nameField);
        addTextPanel.add(empField);
        addTextPanel.add(specField);
        addPanel.add(addLabel);
        addPanel.add(addTextPanel);
        addPanel.add(addButtonPanel);
        addPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        controlsPanel.add(addPanel);
        controlsPanel.add(searchPanel);

        add(controlsPanel, BorderLayout.NORTH);

        add(jsp, BorderLayout.CENTER);

        add(delButton, BorderLayout.SOUTH);

        table.getTableHeader().setReorderingAllowed(false);

        setVisible(true);
        pack();
        setMinimumSize(getBounds().getSize());

        table.setRowSorter(sorter);
    }

    private void init() {
        //Инициализация элементов
        menuBar = new JMenuBar();
        mtm = new MyTableModel(new CompControl());
        addButton = new JButton("Добавить элемент");
        delButton = new JButton("Удалить элемент");
        controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout());
        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addTextPanel = new JPanel();
        addButtonPanel = new JPanel();
        searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        table = new JTable(mtm);
        table.setPreferredScrollableViewportSize(TableSizeDefiner(10, table.getColumnModel().getTotalColumnWidth()));
        jsp = new JScrollPane(table);
        nameField = new JTextField(15);
        empField = new JTextField(15);
        specField = new JTextField(15);
        typeBox = new JComboBox<>(new String[]{
                "Судостроительная компания",
                "Страховая компания",
                "Самолётостроительная компания",
        });
        searchField = new JTextField(15);
        addLabel = new JLabel("Добавление элементов");
        searchLabel = new JLabel("Поиск элементов");
        sorter = new TableRowSorter<MyTableModel>((MyTableModel) table.getModel());

        menuBar.add(createMenu());

        //Подключение listen'ров
        addButton.addActionListener(new MyListener());
        delButton.addActionListener(new MyListener());
        typeBox.addActionListener(new MyListener());
        nameField.getDocument().addDocumentListener(new MyDocumentListener());
        empField.getDocument().addDocumentListener(new MyDocumentListener());
        specField.getDocument().addDocumentListener(new MyDocumentListener());
        searchField.getDocument().addDocumentListener(new MyDocumentListener());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SafetyChecker();
            }
        });

        SafetyChecker();
    }
    //Верхнее меню
    private JMenu createMenu(){
        JMenu menu = new JMenu("Меню");
        JMenuItem add = new JMenuItem("Добавить элемент");
        add.addActionListener(new MyListener());
        JMenuItem del = new JMenuItem("Удалить элемент");
        del.addActionListener(new MyListener());
        menu.add(add);
        menu.addSeparator();
        menu.add(del);
        return menu;
    }

    //Проверка всякого разного
    void SafetyChecker(){
        //Выбрана ли какая-либо строка в таблице?
        if (table.getSelectedRow() == -1){
            delButton.setEnabled(false);
            menuBar.getMenu(0).getItem(2).setEnabled(false);
        }
        else{
            delButton.setEnabled(true);
            menuBar.getMenu(0).getItem(2).setEnabled(true);
        }
        //=================
        if (FieldsChecker()){
            addButton.setEnabled(false);
            menuBar.getMenu(0).getItem(0).setEnabled(false);
        }
        else{
            addButton.setEnabled(true);
            menuBar.getMenu(0).getItem(0).setEnabled(true);
        }
        //==================
        if (srcFieldsCheck()){
            Search(searchField.getText());
        }
    }

    boolean srcFieldsCheck(){
        boolean outMessage = false;
        if(searchField.getText().matches("[A-Za-zА-Яа-я0-9]+")){
            outMessage = true;
        }
        else{
            outMessage = false;
        }
        return outMessage;
    }


    boolean FieldsChecker(){
        boolean outMessage = false;
        if(nameField.getText().equals("")){
            nameField.setBackground(Color.RED);
        }
        else{
            nameField.setBackground(Color.WHITE);
        }
        if(!empField.getText().matches("[0-9]+")){
            empField.setBackground(Color.RED);
        }
        else{
            empField.setBackground(Color.WHITE);
        }
        if(!specField.getText().matches("[0-9]+")){
            specField.setBackground(Color.RED);
        }
        else{
            specField.setBackground(Color.WHITE);
        }

        if ((nameField.getBackground().equals(Color.RED))||(empField.getBackground().equals(Color.RED))||(specField.getBackground().equals(Color.RED))){
            outMessage = true;
        }
        return  outMessage;
    }

    void Search(String text){
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setStringConverter(new TableStringConverter() {
                @Override
                public String toString(TableModel model, int row, int column) {
                    return model.getValueAt(row, column).toString().toLowerCase();
                }
            });

            sorter.setRowFilter(RowFilter.regexFilter(text.toLowerCase()));
        }
    }

    Dimension TableSizeDefiner(int rows, int width){
        int height = table.getRowHeight() * rows;
        return new Dimension(width, height);
    }

    class MyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Удалить элемент")){
                mtm.Remove(table.getSelectedRow());
            }
            else if(e.getActionCommand().equals("Добавить элемент")){
                mtm.Add(nameField.getText(), Integer.parseInt(empField.getText()), Integer.parseInt(specField.getText()), typeBox.getSelectedIndex());
            }
            SafetyChecker();
        }
    }

    class MyDocumentListener implements ValletOr.view.MyDocumentListener {

        @Override
        public void update(DocumentEvent e) {
            SafetyChecker();
        }
    }
}