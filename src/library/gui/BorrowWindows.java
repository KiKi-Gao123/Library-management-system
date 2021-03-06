package library.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import library.bll.BookAdmin;
import library.bll.BorrowAdmin;
import library.bll.DepartmentTypeAdmin;
import library.bll.ReaderAdmin;
import library.bll.ReaderTypeAdmin;
import library.gui.commons.CustomizedTableModel;
import library.model.Book;
import library.model.Borrow;
import library.model.Reader;

public class BorrowWindows extends JFrame {

	/**	
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int times = 0;
	private DefaultTableModel tableModel;
	private ReaderTypeAdmin readerTypeBll = new ReaderTypeAdmin();
	private DepartmentTypeAdmin deptTypeBll = new DepartmentTypeAdmin();
	private ReaderAdmin readerBll = new ReaderAdmin();
	private BorrowAdmin borrowBll = new BorrowAdmin();
	private BookAdmin bookBll = new BookAdmin();

	private JScrollPane bookResultInfoPane;
	private JPanel operatorPanel;
	private JPanel functionPanel;
	private JPanel bookInfoPanel;
	private JScrollPane borrowedPane;
	private JPanel readerInformatiomPanel;

	private JTextField tfRdId;
	private JTextField tfRdName;
	private JTextField tfCanLendQty;
	private JTextField tfRdDept;
	private JTextField tfCanLendDay;
	private JTextField tfRdType;
	private JTextField tfBorrowedQty;
	private JTable borrowedResultTable;
	private JTextField tfBookId;
	private JTextField tfBookName;
	private JTable bookResultInfoTable;

	private JLabel lblOperator;
	private JLabel lblOperatorTime;

	private void initReaderInformatiomPanel() {
		readerInformatiomPanel = new JPanel();
		readerInformatiomPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u8BFB\u8005\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		readerInformatiomPanel.setBounds(10, 10, 864, 77);
		getContentPane().add(readerInformatiomPanel);
		readerInformatiomPanel.setLayout(null);

		JLabel label = new JLabel("???????????????");
		label.setBounds(10, 31, 66, 15);
		readerInformatiomPanel.add(label);

		tfRdId = new JTextField();
		tfRdId.setBounds(74, 29, 66, 21);
		readerInformatiomPanel.add(tfRdId);
		tfRdId.setColumns(10);

		JButton btnQuery = new JButton("??????");
		btnQuery.setBounds(141, 28, 66, 23);
		readerInformatiomPanel.add(btnQuery);
		btnQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ??????????????????????????????
				if (tfRdId.getText().equals("") || tfRdId.getText() == null) {
					JOptionPane.showMessageDialog(null, "???????????????ID???", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Reader reader = readerBll.getReader(Integer.valueOf(tfRdId.getText()));
				setReaderToText(reader);
				// ?????????????????????????????????
				Borrow[] borrows = borrowBll.getBorrowsByrdId(Integer.valueOf(tfRdId.getText()));
				// ????????????
				updateBorrowedResultTable(borrows);
			}
		});

		JLabel label_1 = new JLabel("????????????");
		label_1.setBounds(237, 18, 54, 15);
		readerInformatiomPanel.add(label_1);

		tfRdName = new JTextField();
		tfRdName.setEditable(false);
		tfRdName.setBounds(301, 15, 66, 21);
		readerInformatiomPanel.add(tfRdName);
		tfRdName.setColumns(10);

		JLabel label_2 = new JLabel("???????????????");
		label_2.setBounds(237, 48, 66, 15);
		readerInformatiomPanel.add(label_2);

		tfCanLendQty = new JTextField();
		tfCanLendQty.setEditable(false);
		tfCanLendQty.setBounds(301, 45, 66, 21);
		readerInformatiomPanel.add(tfCanLendQty);
		tfCanLendQty.setColumns(10);

		JLabel label_3 = new JLabel("????????????");
		label_3.setBounds(377, 18, 54, 15);
		readerInformatiomPanel.add(label_3);

		tfRdDept = new JTextField();
		tfRdDept.setEditable(false);
		tfRdDept.setBounds(441, 15, 66, 21);
		readerInformatiomPanel.add(tfRdDept);
		tfRdDept.setColumns(10);

		JLabel label_4 = new JLabel("???????????????");
		label_4.setBounds(377, 48, 66, 15);
		readerInformatiomPanel.add(label_4);

		tfCanLendDay = new JTextField();
		tfCanLendDay.setEditable(false);
		tfCanLendDay.setBounds(441, 45, 66, 21);
		readerInformatiomPanel.add(tfCanLendDay);
		tfCanLendDay.setColumns(10);

		JLabel label_5 = new JLabel("????????????");
		label_5.setBounds(517, 18, 54, 15);
		readerInformatiomPanel.add(label_5);

		tfRdType = new JTextField();
		tfRdType.setEditable(false);
		tfRdType.setBounds(581, 15, 66, 21);
		readerInformatiomPanel.add(tfRdType);
		tfRdType.setColumns(10);

		JLabel label_6 = new JLabel("????????????");
		label_6.setBounds(517, 48, 54, 15);
		readerInformatiomPanel.add(label_6);

		tfBorrowedQty = new JTextField();
		// tfBorrowedQty.setBorder(new LineBorder(new Color(171, 173, 179)));
		tfBorrowedQty.setEditable(false);
		tfBorrowedQty.setBounds(581, 45, 66, 21);
		readerInformatiomPanel.add(tfBorrowedQty);
		tfBorrowedQty.setColumns(10);

		JButton btnCtueBorrow = new JButton("??????");
		btnCtueBorrow.setBounds(667, 27, 73, 23);
		readerInformatiomPanel.add(btnCtueBorrow);
		btnCtueBorrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = borrowedResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "???????????????????????????");
					return;
				}

				System.out.println(times);
				if (times >= 2) {
					JOptionPane.showMessageDialog(null, "??????????????????!");
					return;
				}

				int bkId = Integer.valueOf(borrowedResultTable.getValueAt(selectedRow, 0).toString());
				// System.out.println(borrowBll.getBorrow(bkId).getRdID());
				if (borrowBll.updateBorrowDatePlan(borrowBll.getBorrow(bkId)) > 0) {
					JOptionPane.showMessageDialog(null, "????????????!");
					times = times + 1;
				} else {
					JOptionPane.showMessageDialog(null, "????????????!");
				}
				borrowedResultTable.getSelectionModel().clearSelection();
			}
		});

		JButton btnReturnBook = new JButton("??????");
		btnReturnBook.setBounds(761, 27, 73, 23);
		readerInformatiomPanel.add(btnReturnBook);
		btnReturnBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = borrowedResultTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "???????????????????????????");
					return;
				}
				int bkId = Integer.valueOf(borrowedResultTable.getValueAt(selectedRow, 0).toString());
				// System.out.println("????????????" +bkId);
				Book book = bookBll.getBook(bkId);
				book.setBkStatus("??????");
				Reader reader = readerBll.getReader(borrowBll.getBorrow(bkId).getRdID());
				Borrow[] borrows = borrowBll.getBorrowsByrdId(reader.getRdID());
				reader.setRdBorrowQty(reader.getRdBorrowQty() - 1);
				if (borrowBll.deleteBorrow(borrowBll.getBorrow(bkId)) > 0 && bookBll.updateBkStatus(book) > 0
						&& readerBll.updateReaderBorrowedQty(reader) > 0) {
					JOptionPane.showMessageDialog(null, "????????????!");
					updateBorrowedResultTable(borrows);
				} else {
					JOptionPane.showMessageDialog(null, "????????????!");
				}
				borrowedResultTable.getSelectionModel().clearSelection();
			}
		});
	}

	private void initBorrowedPane() {
		borrowedPane = new JScrollPane();
		borrowedPane.setBackground(Color.DARK_GRAY);
		borrowedPane.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u5DF2\u501F\u56FE\u4E66", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		borrowedPane.setBounds(10, 95, 864, 139);
		getContentPane().add(borrowedPane);

		String[] dispColNames = new String[] { "????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????", "????????????" };
		tableModel = new DefaultTableModel(dispColNames, 0);
		borrowedResultTable = new JTable(tableModel) {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		borrowedPane.add(borrowedResultTable);
		borrowedResultTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// borrowedResultTable.setEnabled(false);
		DefaultTableCellRenderer rtTableCellRenderer = new DefaultTableCellRenderer();
		rtTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		borrowedResultTable.setDefaultRenderer(Object.class, rtTableCellRenderer);
		borrowedPane.setViewportView(borrowedResultTable);
	}

	private void initBookResultInfoPane() {
		bookResultInfoPane = new JScrollPane();
		bookResultInfoPane.setBounds(10, 303, 864, 247);
		getContentPane().add(bookResultInfoPane);

		// DefaultTableModel tableModel = new DefaultTableModel(arg0, arg1)
		CustomizedTableModel<Book> tableModel1 = new CustomizedTableModel<Book>(bookBll.getDisplayColumnNames(),
				bookBll.getMethodNames());
		bookResultInfoTable = new JTable(tableModel1);
		bookResultInfoPane.add(bookResultInfoTable);
		bookResultInfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableCellRenderer rTableCellRenderer = new DefaultTableCellRenderer();
		rTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		bookResultInfoTable.setDefaultRenderer(Object.class, rTableCellRenderer);
		// bookResultInfoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		bookResultInfoPane.setViewportView(bookResultInfoTable);
	}

	private void initFunctionPanel() {
		functionPanel = new JPanel();
		functionPanel.setBounds(10, 607, 864, 44);
		getContentPane().add(functionPanel);
		functionPanel.setLayout(null);

		JButton btnBorrowed = new JButton("??????");
		btnBorrowed.setBounds(316, 10, 93, 23);
		functionPanel.add(btnBorrowed);
		btnBorrowed.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = bookResultInfoTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "?????????????????????", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int bkId = Integer.valueOf(bookResultInfoTable.getValueAt(selectedRow, 0).toString());
				Book book = bookBll.getBook(bkId);
				book.setBkStatus("??????");
				Login login = new Login();
				Reader reader = readerBll.getReader(Integer.valueOf(login.getTfUsername()));
				reader.setRdBorrowQty(reader.getRdBorrowQty() + 1);
				if (reader.getRdStatus().equals("??????")) {
					JOptionPane.showMessageDialog(null, "???????????????????????????????????????!", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Borrow borrow = new Borrow();
				borrow.setRdID(reader.getRdID());
				borrow.setBkID(book.getBkID());
				if (borrowBll.addBorrow(borrow) > 0 && bookBll.updateBkStatus(book) > 0
						&& readerBll.updateReaderBorrowedQty(reader) > 0) {
					JOptionPane.showMessageDialog(null, "????????????!");
					Date date = new Date();
					lblOperatorTime.setText(date.toString());
					lblOperator.setText(borrowBll.getBorrow(bkId).getOperatorLend());
				} else {
					JOptionPane.showMessageDialog(null, "????????????!");
				}
				bookResultInfoTable.getSelectionModel().clearSelection();
			}
		});

		JButton btnReturn = new JButton("??????");
		btnReturn.setBounds(450, 10, 93, 23);
		functionPanel.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				closeGUI();
			}
		});
	}

	private void initOperatorPanel() {
		operatorPanel = new JPanel();
		operatorPanel.setBorder(new LineBorder(Color.GRAY));
		operatorPanel.setBounds(10, 560, 864, 37);
		getContentPane().add(operatorPanel);
		operatorPanel.setLayout(null);

		JLabel label_7 = new JLabel("?????????");
		label_7.setBounds(10, 11, 54, 15);
		label_7.setFont(new Font("??????", Font.PLAIN, 12));
		operatorPanel.add(label_7);

		lblOperator = new JLabel("");
		lblOperator.setBounds(74, 11, 70, 15);
		lblOperator.setForeground(Color.red);
		lblOperator.setFont(new Font("??????", Font.PLAIN, 12));
		operatorPanel.add(lblOperator);

		lblOperatorTime = new JLabel("");
		lblOperatorTime.setBounds(683, 11, 171, 15);
		operatorPanel.add(lblOperatorTime);
	}

	private void initBookInfoPanel() {
		bookInfoPanel = new JPanel();
		bookInfoPanel.setBorder(
				new TitledBorder(null, "\u56FE\u4E66\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bookInfoPanel.setBounds(10, 243, 864, 50);
		getContentPane().add(bookInfoPanel);
		bookInfoPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("????????????");
		lblNewLabel.setBounds(30, 22, 54, 15);
		bookInfoPanel.add(lblNewLabel);

		tfBookId = new JTextField();
		tfBookId.setBounds(84, 19, 92, 21);
		bookInfoPanel.add(tfBookId);
		tfBookId.setColumns(10);

		JButton btnIdQuery = new JButton("????????????");
		btnIdQuery.setBounds(186, 18, 92, 23);
		bookInfoPanel.add(btnIdQuery);
		btnIdQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tfBookId.getText().equals("") || tfBookId.getText() == null) {
					JOptionPane.showMessageDialog(null, "????????????????????????!");
					return;
				}
				Book[] books = bookBll.getBooksById(Integer.valueOf(tfBookId.getText()));
				updateBookResultInfoTable(books);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("????????????");
		lblNewLabel_1.setBounds(301, 22, 54, 15);
		bookInfoPanel.add(lblNewLabel_1);

		tfBookName = new JTextField();
		tfBookName.setBounds(365, 19, 81, 21);
		bookInfoPanel.add(tfBookName);
		tfBookName.setColumns(10);

		JButton btnNameQuery = new JButton("????????????");
		btnNameQuery.setBounds(456, 18, 92, 23);
		bookInfoPanel.add(btnNameQuery);
		btnNameQuery.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Book[] books = bookBll.getBookInfosBybkName(tfBookName.getText());
				updateBookResultInfoTable(books);
			}
		});
	}

	private void setReaderToText(Reader reader) {
		tfRdName.setText(reader.getRdName());
		// ??????????????????????????????????????????????????????????????????
		tfRdType.setText(readerTypeBll.getObjectByID(reader.getRdType()).getRdTypeName());
		// ??????????????????????????????????????????????????????????????????
		tfRdDept.setText(deptTypeBll.getObjectById(reader.getRdDept()).getDtName());
		// ????????????????????????????????????????????????????????????????????????????????????
		tfCanLendDay.setText(String.valueOf(readerTypeBll.getObjectByID(reader.getRdType()).getCanLendDay()));
		tfCanLendQty.setText(String.valueOf(readerTypeBll.getObjectByID(reader.getRdType()).getCanLendQty()));
		tfBorrowedQty.setText(String.valueOf(reader.getRdBorrowQty()));
	}

	// ????????????????????????;
	private void updateBorrowedResultTable(Borrow[] borrows) {
		// System.out.println("??????1");
		if (borrows == null) {
			JOptionPane.showMessageDialog(null, "?????????????????????????????????");
			return;
		}
		tableModel.setRowCount(0);
		// System.out.println(borrows.length);
		for (Borrow borrow : borrows) {
			// ??????????????????????????????
			Vector<String> vector = new Vector<String>();
			System.out.println(borrow.getBkID());
			vector.add(String.valueOf(borrow.getBkID()));
			vector.add(bookBll.getBook(borrow.getBkID()).getBkName());
			vector.add(bookBll.getBook(borrow.getBkID()).getBkAuthor());
			vector.add(String.valueOf(borrow.getIdContinueTimes()));
			vector.add(borrow.getIdDateOut().toString());
			vector.add(borrow.getIdDateRetPlan().toString());
			vector.add(String.valueOf(borrow.getIdOverDay()));
			vector.add(String.valueOf(borrow.getIdOverMoney()));
			// ????????????????????????????????????
			System.out.println(vector);
			tableModel.addRow(vector);
			vector = null;
		}
	}

	// ????????????????????????;
	private void updateBookResultInfoTable(Book[] books) {
		if (books == null) {
			JOptionPane.showMessageDialog(null, "???????????????????????????!");
			return;
		}
		@SuppressWarnings("unchecked")
		CustomizedTableModel<Book> tableModel = (CustomizedTableModel<Book>) bookResultInfoTable.getModel();
		tableModel.setRecords(books);
		// ????????????
		tableModel.fireTableDataChanged();
	}

	// ????????????
	private void closeGUI() {
		this.dispose();
	}

	public BorrowWindows() {
		setResizable(false);
		setSize(new Dimension(900, 700));
		setTitle("????????????");
		getContentPane().setLayout(null);

		initBookResultInfoPane();
		initFunctionPanel();
		initOperatorPanel();
		initBookInfoPanel();
		initBorrowedPane();
		initReaderInformatiomPanel();
	}

}
