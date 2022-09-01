package ui;

import model.Capybara;
import model.EventLog;
import model.Event;
import model.Food;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


// A GUI representation of the Capybara interaction with Kiwi. Contains the buttons for interaction, and a visual
// representation of Kiwi and his reactions to the food
public class CapybaraGUI extends JFrame implements ActionListener, ListSelectionListener {
    private JFrame mainFrame;

    JPanel mainPanel;
    JPanel capybaraPanel;
    JPanel dialoguePanel;
    JPanel instructionPanel;
    JPanel buttonPanel;
    JPanel feedPanel;
    JPanel historyPanel;
    JPanel caloriePanel;

    JScrollPane historyDisplay;

    JLabel capybaraImage;
    JLabel dialogueLabel;
    JLabel instructionLabel;
    JLabel counter;

    JButton feedButton;
    JButton saveHistoryButton;
    JButton loadHistoryButton;
    JButton viewHistoryButton;
    JButton quitButton;

    JButton watermelonButton;
    JButton bermudaGrassButton;
    JButton freshLinenButton;
    JButton libraryBookButton;
    JButton backButton;

    JList historyList;

    DefaultListModel listModel;

    CapybaraLog capybaraLog;
    Capybara kiwi;

    // EFFECTS: Sets up the GUI and mainFrame for the program, creates layout with panels and adds in the buttons
    public CapybaraGUI() {
        setMainFrame();

        dialogueLabel = new JLabel("Kiwi Awaits Patiently");
        instructionLabel = new JLabel("Choose Your Action");

        dialogueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        instructionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        capybaraPanel = new JPanel();
        mainPanel = new JPanel();
        dialoguePanel = new JPanel();
        instructionPanel = new JPanel();
        buttonPanel = new JPanel();

        capybaraLog = new CapybaraLog();
        kiwi = new Capybara();

        addMainButtons();
        addPanelsAndFrames();
        setMainButtonFont();
    }

    // EFFECTS: sets up the mainFrame and closing operation
    public void setMainFrame() {
        mainFrame = new JFrame("Craving Capybaras");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000,1000);
        mainFrame.setLocation(1500, 500);

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog(EventLog.getInstance());
            }
        });
    }


    // MODIFIES: mainPanel, mainFrame
    // EFFECTS: adds the basic panels and frame for the main GUI of the program
    public void addPanelsAndFrames() {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainPanel.add(capybaraPanel);
        mainPanel.add(dialoguePanel);
        mainPanel.add(instructionPanel);
        mainPanel.add(buttonPanel);
        mainFrame.add(mainPanel);

        capybaraImage = new JLabel(new ImageIcon("./lib/images/normalcapy.jpg"));
        capybaraPanel.add(capybaraImage);

        dialoguePanel.add(dialogueLabel);
        instructionPanel.add(instructionLabel);

        capybaraPanel.setVisible(true);
        dialoguePanel.setVisible(true);
        instructionPanel.setVisible(true);
        buttonPanel.setVisible(true);
        mainFrame.setVisible(true);
    }

    // MODIFIES: buttonPanel
    // EFFECTS: adds the buttons used in the main frame to interact with the user
    public void addMainButtons() {
        feedButton = new JButton("Feed Kiwi");
        viewHistoryButton = new JButton("History");
        saveHistoryButton = new JButton("Save History");
        loadHistoryButton = new JButton("Load History");
        quitButton = new JButton("Quit");

        feedButton.setActionCommand("feed");
        viewHistoryButton.setActionCommand("history");
        saveHistoryButton.setActionCommand("save");
        loadHistoryButton.setActionCommand("load");
        quitButton.setActionCommand("quit");

        feedButton.addActionListener(this);
        viewHistoryButton.addActionListener(this);
        saveHistoryButton.addActionListener(this);
        loadHistoryButton.addActionListener(this);
        quitButton.addActionListener(this);

        buttonPanel.add(feedButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(saveHistoryButton);
        buttonPanel.add(loadHistoryButton);
        buttonPanel.add(quitButton);
    }

    // EFFECTS: sets the font style and size for the main page buttons and labels
    public void setMainButtonFont() {
        feedButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        viewHistoryButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        saveHistoryButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        loadHistoryButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        quitButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));

        instructionLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        dialogueLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
    }

    // MODIFIES: mainFrame
    // EFFECTS: an event handler for the user when they click buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("feed")) {
            feedDisplay();
        } else if (e.getActionCommand().equals("history")) {
            viewHistory();
        } else if (e.getActionCommand().equals("save")) {
            saveHistory();
        } else if (e.getActionCommand().equals("load")) {
            loadHistory();
        } else if (e.getActionCommand().equals("quit")) {
            mainFrame.dispose();
            printLog(EventLog.getInstance());
        } else if (e.getActionCommand().equals("feedBack")) {
            feedBack();
        } else if (e.getActionCommand().equals("historyBack")) {
            historyBack();
        }

        feedingAction(e);
    }

    // MODIFIES: mainFrame
    // EFFECTS: an event handler for the user when they are feeding Kiwi
    public void feedingAction(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "watermelon":
                feedWatermelon();
                break;
            case "freshLinen":
                feedFreshLinen();
                break;
            case "bermudaGrass":
                feedBermudaGrass();
                break;
            case "libraryBook":
                feedLibraryBook();
                break;
        }
    }

    // MODIFIES: mainFrame
    // EFFECTS: feeds a watermelon to Kiwi and updates image and response
    public void feedWatermelon() {
        feedKiwi(capybaraLog.getWatermelon());
        capybaraImage.setIcon(new ImageIcon("./lib/images/happycapy.jpg"));
        dialogueLabel.setText(kiwi.getMood().getMessage());
        instructionLabel.setText(kiwi.getMood().getNarration());
    }

    // MODIFIES: mainFrame
    // EFFECTS: feeds bermuda grass to Kiwi and updates image and response
    public void feedBermudaGrass() {
        feedKiwi(capybaraLog.getBermudaGrass());
        capybaraImage.setIcon(new ImageIcon("./lib/images/normalcapy.jpg"));
        dialogueLabel.setText(kiwi.getMood().getMessage());
        instructionLabel.setText(kiwi.getMood().getNarration());
    }

    // MODIFIES: mainFrame
    // EFFECTS: feeds a library book to Kiwi and updates image and response
    public void feedLibraryBook() {
        feedKiwi(capybaraLog.getLibraryBook());
        capybaraImage.setIcon(new ImageIcon("./lib/images/sadcapy.jpg"));
        dialogueLabel.setText(kiwi.getMood().getMessage());
        instructionLabel.setText(kiwi.getMood().getNarration());
    }

    // MODIFIES: mainFrame
    // EFFECTS: feeds fresh linen watermelon to Kiwi and updates image and response
    public void feedFreshLinen() {
        feedKiwi(capybaraLog.getFreshLinen());
        capybaraImage.setIcon(new ImageIcon("./lib/images/angrycapy.jpg"));
        dialogueLabel.setText(kiwi.getMood().getMessage());
        instructionLabel.setText(kiwi.getMood().getNarration());
    }

    // MODIFIES: mainPanel, mainFrame
    // EFFECTS: returns main frame back to the main page from the feeding page
    public void feedBack() {
        mainPanel.remove(feedPanel);
        mainPanel.add(buttonPanel);
        instructionLabel.setText("Choose your action:");

        mainFrame.repaint();
        validate();
    }

    // MODIFIES: mainPanel, mainFrame
    // EFFECTS: returns main frame back to the main page from the history page
    public void historyBack() {
        mainPanel.remove(historyDisplay);
        mainPanel.remove(historyPanel);
        mainPanel.remove(caloriePanel);
        mainPanel.add(buttonPanel);
        instructionLabel.setText("Choose your action:");

        mainFrame.setVisible(true);
        mainFrame.repaint();
    }

    // MODIFIES: mainPanel, mainFrame
    // EFFECTS: changes the main page to the history page, adding in the history, buttons and calorie panels
    public void viewHistory() {
        historyPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        backButton.setActionCommand("historyBack");
        backButton.addActionListener(this);
        historyPanel.add(backButton);

        instructionLabel.setText("Kiwi has eaten:");
        listDisplay();

        mainPanel.remove(buttonPanel);
        mainPanel.add(historyDisplay);
        mainPanel.add(caloriePanel);
        mainPanel.add(historyPanel);

        mainFrame.setVisible(true);
        mainFrame.repaint();
    }

    // MODIFIES: caloriePanel, listModel, counter, historyDisplay
    // EFFECTS: displays the history in list form, updates the GUI as user clicks on specific items in the list
    public void listDisplay() {
        listModel = new DefaultListModel();
        caloriePanel = new JPanel();
        JLabel calorieLabel = new JLabel("Total number of calories: ");
        calorieLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        counter = new JLabel();
        counter.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        caloriePanel.add(calorieLabel);
        caloriePanel.add(counter);

        for (Food f : capybaraLog.getHistory().getHistory()) {
            listModel.addElement(f.getName());
        }

        historyList = new JList(listModel);
        historyList.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        historyList.addListSelectionListener(this);
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        historyDisplay = new JScrollPane(historyList);
        historyDisplay.setPreferredSize(new Dimension(500, 200));

        repaint();
    }

    // MODIFIES: counter
    // EFFECTS: event handler for the list, displays number of calories based on the food selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            String calorie = kiwi.calorieCounter(historyList.getSelectedValue().toString());
            counter.setText(calorie);
        }
    }

    // EFFECTS: saves the history so far and updates dialogue
    public void saveHistory() {
        dialogueLabel.setText("History is saved");
        instructionLabel.setText("Choose your action:");
        capybaraLog.saveHistory();
    }

    // EFFECTS: loads the history from database and updates dialogue
    public void loadHistory() {
        dialogueLabel.setText("History has been loaded");
        instructionLabel.setText("Choose your action:");
        capybaraLog.loadHistory();
    }

    // MODIFIES: mainFrame
    // EFFECTS: updates main page to feeding page by adding food buttons and instructions
    public void feedDisplay() {
        feedPanel = new JPanel();
        dialogueLabel.setText("Kiwi stares at you hungrily");
        instructionLabel.setText("What do you want to feed Kiwi?");

        addFeedButtons();
        setFeedButtonFont();

        mainPanel.add(feedPanel);
        mainPanel.remove(buttonPanel);
        validate();
        feedPanel.setVisible(true);
    }

    // EFFECTS: sets the font for the buttons used to feed Kiwi
    public void setFeedButtonFont() {
        watermelonButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        freshLinenButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        libraryBookButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        bermudaGrassButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
    }

    // MODIFIES: History, Capybara
    // EFFECTS: feeds kiwi the food item
    public void feedKiwi(Food food) {
        kiwi.feedCapybara(food, capybaraLog.getHistory());
    }

    // MODIFIES: feedPanel
    // EFFECTS: adds the button options for food items you can feed to Kiwi
    private void addFeedButtons() {
        watermelonButton = new JButton("Watermelon");
        freshLinenButton = new JButton("Fresh Linen");
        bermudaGrassButton = new JButton("Bermuda Grass");
        libraryBookButton = new JButton("Library Book");

        backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        backButton.setActionCommand("feedBack");
        backButton.addActionListener(this);

        feedPanel.add(watermelonButton);
        feedPanel.add(freshLinenButton);
        feedPanel.add(bermudaGrassButton);
        feedPanel.add(libraryBookButton);
        feedPanel.add(backButton);

        watermelonButton.setActionCommand("watermelon");
        freshLinenButton.setActionCommand("freshLinen");
        bermudaGrassButton.setActionCommand("bermudaGrass");
        libraryBookButton.setActionCommand("libraryBook");

        watermelonButton.addActionListener(this);
        freshLinenButton.addActionListener(this);
        bermudaGrassButton.addActionListener(this);
        libraryBookButton.addActionListener(this);
    }

    // EFFECTS: prints the log of events from interacting with Kiwi
    public void printLog(EventLog el) {
        String log = "";
        for (Event next : el) {
            log = log + next.toString() + "\n\n";

        }
        System.out.println(log);
    }



}
