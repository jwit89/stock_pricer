import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
 
public class StockGUI extends JFrame{
	JButton calculateButton = new JButton("Calculate");
	JButton exitButton = new JButton("Exit");
	
	JRadioButton callButton = new JRadioButton("Call");
	JRadioButton putButton = new JRadioButton("Put");
	ButtonGroup optionType = new ButtonGroup();
	
	
	// input fields
	JLabel symbolLabel = new JLabel("Stock Symbol", JLabel.CENTER);
	JTextField symbolField = new JTextField(10);
	JLabel stockPriceLabel = new JLabel("Stock Price", JLabel.CENTER);
	JTextField stockPriceField = new JTextField(10);
	JLabel todayDateLabel = new JLabel("Date Today mm/dd/yyyy", JLabel.CENTER);
	JTextField todayDateField = new JTextField(10);
	JLabel interestRateLabel = new JLabel("Interest Rate", JLabel.CENTER);
	JTextField interestRateField = new JTextField(10);
	JLabel strikePriceLabel = new JLabel("Strike Price", JLabel.CENTER);
	JTextField strikePriceField = new JTextField(10);
	JLabel expirationLabel = new JLabel("Expiration Date mm/dd/yyyy", JLabel.CENTER);
	JTextField expirationField = new JTextField(10);
	JLabel optionPriceLabel = new JLabel("Option Price", JLabel.CENTER);
	JTextField optionPriceField = new JTextField(10);
	
	// output fields
	JLabel thirtyAVGLabel = new JLabel("30-Day Avg DCGR", JLabel.CENTER);
	JTextField thirtyAVGField = new JTextField(10);
	JLabel thirtySTDEVLabel = new JLabel("30-Day STDEV DCGR", JLabel.CENTER);
	JTextField thirtySTDEVField = new JTextField(10);
	JLabel sixtyAVGLabel = new JLabel("60-Day Avg DCGR", JLabel.CENTER);
	JTextField sixtyAVGField = new JTextField(10);
	JLabel sixtySTDEVLabel = new JLabel("60-Day STDEV DCGR", JLabel.CENTER);
	JTextField sixtySTDEVField = new JTextField(10);
	JLabel yearAVGLabel = new JLabel("365-Day Avg DCGR", JLabel.CENTER);
	JTextField yearAVGField = new JTextField(10);
	JLabel yearSTDEVLabel = new JLabel("365-Day STDEV DCGR", JLabel.CENTER);
	JTextField yearSTDEVField = new JTextField(10);
	JLabel dtmLabel = new JLabel("Days to Maturity", JLabel.CENTER);
	JTextField dtmField = new JTextField(10);
	JLabel impliedVolatilityLabel = new JLabel("Implied Daily Volatility", JLabel.CENTER);
	JTextField impliedVolatilityField = new JTextField(10);
	JLabel timeDecayLabel = new JLabel("One-Day Time Decay", JLabel.CENTER);
	JTextField timeDecayField = new JTextField(10);
	
	
	
	
	GridLayout buttonLayout = new GridLayout(0,2);
	
	public StockGUI(String name){
		super(name);
		setResizable(false);
	}
	
	public void addComponentstoPane(final Container pane){
		final JPanel inputPanel = new JPanel();
		inputPanel.setLayout(buttonLayout);
		final JPanel outputPanel = new JPanel();
		outputPanel.setLayout(buttonLayout);
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(1,2));
		
		optionType.add(callButton);
		optionType.add(putButton);
		callButton.setSelected(true);
		
		// add a spacer
		inputPanel.add(new JLabel(""));
		inputPanel.add(new JLabel(""));
		
		// add the call/put choice
		inputPanel.add(callButton);
		inputPanel.add(putButton);
		
		inputPanel.add(new JLabel(""));
		inputPanel.add(new JLabel(""));
		
		// add all the input labels and text fields to the main panel
		inputPanel.add(symbolLabel);
		inputPanel.add(symbolField);
		inputPanel.add(stockPriceLabel);
		inputPanel.add(stockPriceField);
		inputPanel.add(todayDateLabel);
		inputPanel.add(todayDateField);
		inputPanel.add(interestRateLabel);
		inputPanel.add(interestRateField);
		inputPanel.add(strikePriceLabel);
		inputPanel.add(strikePriceField);
		inputPanel.add(expirationLabel);
		inputPanel.add(expirationField);
		inputPanel.add(optionPriceLabel);
		inputPanel.add(optionPriceField);
		
		// add a spacer
		inputPanel.add(new JLabel(""));
		inputPanel.add(new JLabel(""));
		
		// disable the output text fields
		thirtyAVGField.setEditable(false);
		thirtySTDEVField.setEditable(false);
		sixtyAVGField.setEditable(false);
		sixtySTDEVField.setEditable(false);
		yearAVGField.setEditable(false);
		yearSTDEVField.setEditable(false);
		dtmField.setEditable(false);
		impliedVolatilityField.setEditable(false);
		timeDecayField.setEditable(false);
		
		// set the alignment of all text fields
		symbolField.setHorizontalAlignment(JTextField.CENTER);
		stockPriceField.setHorizontalAlignment(JTextField.CENTER);
		todayDateField.setHorizontalAlignment(JTextField.CENTER);
		interestRateField.setHorizontalAlignment(JTextField.CENTER);
		strikePriceField.setHorizontalAlignment(JTextField.CENTER);
		expirationField.setHorizontalAlignment(JTextField.CENTER);
		optionPriceField.setHorizontalAlignment(JTextField.CENTER);
		
		thirtyAVGField.setHorizontalAlignment(JTextField.CENTER);
		thirtySTDEVField.setHorizontalAlignment(JTextField.CENTER);
		sixtyAVGField.setHorizontalAlignment(JTextField.CENTER);
		sixtySTDEVField.setHorizontalAlignment(JTextField.CENTER);
		yearAVGField.setHorizontalAlignment(JTextField.CENTER);
		yearSTDEVField.setHorizontalAlignment(JTextField.CENTER);
		
		dtmField.setHorizontalAlignment(JTextField.CENTER);
		impliedVolatilityField.setHorizontalAlignment(JTextField.CENTER);
		timeDecayField.setHorizontalAlignment(JTextField.CENTER);
		
		
		// put today's date in the date field
		todayDateField.setText(CalUtils.getCurrentDate());
		
		// put a preliminary interest rate in the interest rate field
		interestRateField.setText("0.01");
		
		// add all the output labels and text fields to the main panel
		outputPanel.add(thirtyAVGLabel);
		outputPanel.add(thirtyAVGField);
		outputPanel.add(thirtySTDEVLabel);
		outputPanel.add(thirtySTDEVField);
		outputPanel.add(sixtyAVGLabel);
		outputPanel.add(sixtyAVGField);
		outputPanel.add(sixtySTDEVLabel);
		outputPanel.add(sixtySTDEVField);
		outputPanel.add(yearAVGLabel);
		outputPanel.add(yearAVGField);
		outputPanel.add(yearSTDEVLabel);
		outputPanel.add(yearSTDEVField);
		outputPanel.add(new JLabel(""));
		outputPanel.add(new JLabel(""));
		outputPanel.add(dtmLabel);
		outputPanel.add(dtmField);
		outputPanel.add(impliedVolatilityLabel);
		outputPanel.add(impliedVolatilityField);
		outputPanel.add(timeDecayLabel);
		outputPanel.add(timeDecayField);
		
		// add a spacer
		outputPanel.add(new JLabel(""));
		outputPanel.add(new JLabel(""));
		outputPanel.add(new JLabel("(c) Jon Witte 2012", JLabel.CENTER));
		outputPanel.add(new JLabel(""));
		outputPanel.add(new JLabel(""));
		outputPanel.add(new JLabel(""));
		
		// add the calculate and exit buttons to the control panel
		controls.add(calculateButton);
		controls.add(exitButton);
		
		// process the exit button press
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// exit the program
				System.exit(0);
			}
		});
		
		// process the calculate button press
		calculateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					// read in the stock symbol
					String symbol = symbolField.getText();
					
					// read in and parse today's date
					// and generate last year's date
					String[] todayString =
							todayDateField.getText().split("/");
					// today: 0 = month, 1 = day, 2 = year
					int[] today = new int[3];
					for (int i = 0; i < today.length; i++)
						today[i] = Integer.parseInt(todayString[i]);
					int[] yearAgo = new int[3];
					for (int i = 0; i < today.length; i++)
						yearAgo[i] = today[i];
					// one year ago
					yearAgo[2]--;
					// deal with leap years
					if (today[0] == 2)
						if (today[1] == 29)
							yearAgo[1] = 28;
					
					// compute the dtm
					int dtm = CalUtils.getDaysBetween(todayDateField.getText(),
							expirationField.getText());
					dtmField.setText(""+dtm);
					
					// generate array containing start and year ago dates, as well as symbol
					String[] historicInput = new String[7];
					historicInput[0] = symbol;
					for (int i = 0; i < yearAgo.length; i++)
						historicInput[i+1] = yearAgo[i]+"";
					for (int i = 0; i < today.length; i++)
						historicInput[i+4] = today[i]+"";
					// generate historic Quotes object from this array
					Quotes historicQuotes = StockHarvester.processData(historicInput);
					
					// output historic avg dcgrs and volatilities
					// weekends count as days
					int thirty = 22;
					int sixty = 44;
					int yearly = 251;
					DecimalFormat df = new DecimalFormat("#.#####");
					thirtyAVGField.setText(""+df.format(historicQuotes.getAverageDCGR(thirty)));
					sixtyAVGField.setText(""+df.format(historicQuotes.getAverageDCGR(sixty)));
					yearAVGField.setText(""+df.format(historicQuotes.getAverageDCGR(yearly)));
					thirtySTDEVField.setText(""+df.format(historicQuotes.getVolatilityDCGR(thirty)));
					sixtySTDEVField.setText(""+df.format(historicQuotes.getVolatilityDCGR(sixty)));
					yearSTDEVField.setText(""+df.format(historicQuotes.getVolatilityDCGR(yearly)));
					
					// read in stock price
					double stockPrice = Double.parseDouble(stockPriceField.getText());
					// read in strike price
					double strikePrice = Double.parseDouble(strikePriceField.getText());
					// read in interest rate
					double interestRate = Double.parseDouble(interestRateField.getText());
					// read in option price
					double optionPrice = Double.parseDouble(optionPriceField.getText());
					// read in the type of option
					String optType = "call";
					if (callButton.isSelected())
						optType = "call";
					if (putButton.isSelected())
						optType = "put";
					
					// calculate the idv
					double impliedDailyVolatility = 
							StockStats.getImpliedVolatility(dtm, stockPrice, strikePrice, 
									interestRate, optionPrice, optType);
					// report the idv
					impliedVolatilityField.setText(df.format(impliedDailyVolatility));
					
					// calculate the time decay
					double timeDecay =
							StockStats.getTimeDecay(dtm, stockPrice, strikePrice,
									interestRate, optionPrice, optType, impliedDailyVolatility);
					// report the time decay
					DecimalFormat df2 = new DecimalFormat("#.###");
					timeDecayField.setText(df2.format(timeDecay));
					
							
				} catch (Exception ex){
					JOptionPane.showMessageDialog(null, "Incorrect or insufficient input.");
				}
			}
		});
		
		pane.add(inputPanel, BorderLayout.NORTH);
		pane.add(outputPanel, BorderLayout.CENTER);
		pane.add(controls, BorderLayout.SOUTH);
	}
	
	// Create and display the GUI
	private static void createAndShowGUI(){
		// create and set up the window
		StockGUI frame = new StockGUI("Stock Options");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set up the content pane
		frame.addComponentstoPane(frame.getContentPane());
		// display the window
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch(UnsupportedLookAndFeelException ex){
			ex.printStackTrace();
		} catch(IllegalAccessException ex){
			ex.printStackTrace();
		} catch(InstantiationException ex){
			ex.printStackTrace();
		} catch(ClassNotFoundException ex){
			ex.printStackTrace();
		}
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
		
	}
}
