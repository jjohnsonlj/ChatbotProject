package chatbot.infra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import chatbot.infra.Chatbot;

public class ChatbotGUI extends JFrame {

	static private Chatbot nowChatbot;
	
	static private JFrame nowGUIFrame;
	
	static private JTextField inputTextBox;
	static private JTextPane chatHistoryPane;
	static private JScrollPane scroll;
	
	static private JButton psuButton;
	
	public ChatbotGUI(Chatbot nowChatbot) {
		
		this.nowChatbot = nowChatbot;
		
		/*
		 * Task 1: Make the interface prettier!
		 * 
		 * As you can see, this graphical interface (GUI) is not pretty. Please
		 * modify the following codes to move the components around or change
		 * their appearances, such as color or size, to make the interface
		 * looks nicer. Please explain what you did in your video.
		 */
		
		//create the frame of chatbot
		nowGUIFrame = new JFrame();
		nowGUIFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		nowGUIFrame.setVisible(true);
		nowGUIFrame.setResizable(false);
		nowGUIFrame.setLayout(null);
		nowGUIFrame.setSize(600, 600);
		nowGUIFrame.setTitle("Chatbot "+nowChatbot.getBotName()+"");
		
		//create JTextPane
		chatHistoryPane = new JTextPane();
		chatHistoryPane.setFont(new Font("Arial", Font.PLAIN, 14));
		nowGUIFrame.add(chatHistoryPane);
		chatHistoryPane.setSize(570, 450);
		chatHistoryPane.setLocation(3, 2);
		chatHistoryPane.setBackground(new Color(240, 248, 255));  // Alice Blue background
        chatHistoryPane.setBorder(new EmptyBorder(10, 10, 10, 10));  // Add padding
		
		//create JTextField
		inputTextBox = new JTextField();
		inputTextBox.setFont(new Font("Arial", Font.PLAIN, 14));
		nowGUIFrame.add(inputTextBox);
		inputTextBox.setBounds(10, 480, 450, 40);
		inputTextBox.addActionListener(new InputTextListener(inputTextBox, chatHistoryPane, this));
	
		
		//create JButton
		psuButton = new JButton("Send", null);
		nowGUIFrame.add(psuButton);
		psuButton.setBounds(470, 480, 100, 40);
		psuButton.setFont(new Font("Arial", Font.BOLD, 14));
		psuButton.setBackground(new Color(70, 130, 180));  // Steel blue (darker shade)
		psuButton.setForeground(Color.WHITE);  // White text
		psuButton.setOpaque(true);  // Ensure the background color is applied
		psuButton.setBorderPainted(false);  // Remove the border
		psuButton.setFocusPainted(false);  // Remove focus border
		psuButton.addActionListener(new ButtonListener(inputTextBox, chatHistoryPane, this));
	
	}

	public ChatbotGUI() {
		
	}
	
	public Chatbot getChatbot() {
		return nowChatbot;
	}
	
	public static void appendToPane(JTextPane nowPane, String senderName, String message, Color color){
		
		String nowMsg = senderName+": "+message+"\n";
		
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Helvetica");
        aset = sc.addAttribute(aset, StyleConstants.FontSize, 16);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = nowPane.getDocument().getLength();
        nowPane.setCaretPosition(len);
        nowPane.setCharacterAttributes(aset, false);
        nowPane.replaceSelection(nowMsg);
        
    }
	
	// TASK 2 with the buttons
}

class ButtonListener implements ActionListener {

    private ChatbotGUI chatbotUtil;
    private JTextField nowInputTextBox;
    private JTextPane nowChatHistoryPane;
    
    public ButtonListener(JTextField inputTextBox, JTextPane chatHistoryPane, ChatbotGUI chatbotUtil) {
        this.chatbotUtil = chatbotUtil;
        nowInputTextBox = inputTextBox;
        nowChatHistoryPane = chatHistoryPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Retrieve user input
        String userInput = nowInputTextBox.getText();
        
        // Avoid empty messages
        if (!userInput.isEmpty()) {
            // Append user input to chat history pane in blue
            ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), userInput, Color.BLUE);
            
            // Get response and append it to the chat history pane in black
            String nowChatbotResponse = chatbotUtil.getChatbot().getResponse(userInput);
            ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), nowChatbotResponse, Color.BLACK);
            
            // Reset input text field
            nowInputTextBox.setText("");
        }
    }
}


class InputTextListener implements ActionListener{
	
	private ChatbotGUI chatbotUtil;
	
	private JTextField nowInputTextBox;
	private JTextPane nowChatHistoryPane;
	
	public InputTextListener(JTextField inputTextBox, JTextPane chatHistoryPane, ChatbotGUI chatbotUtil) {
		this.chatbotUtil = chatbotUtil;
		nowInputTextBox = inputTextBox;
		nowChatHistoryPane = chatHistoryPane;
		//nowUserName = userName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nowInputText = nowInputTextBox.getText();
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getUserName(), nowInputText, Color.GRAY);
		
		String nowChatbotResponse = chatbotUtil.getChatbot().getResponse(nowInputText);
		ChatbotGUI.appendToPane(nowChatHistoryPane, chatbotUtil.getChatbot().getBotName(), nowChatbotResponse, Color.BLACK);
		
		nowInputTextBox.setText("");
		
	}
		
}


