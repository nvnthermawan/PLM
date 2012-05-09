package jlm.core.ui;

import jlm.core.model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CourseChooseDialog extends JDialog {

    private static final long serialVersionUID = 2234402839093122248L;

    protected ArrayList<String> courseListIDs;
    protected JList jListID = null;
    protected JButton OKButton;
    protected JPasswordField passwordField;


    public CourseChooseDialog() {
        super(MainFrame.getInstance(), "JLM Course", false);

        initComponent(this.getContentPane());

        setMinimumSize(new Dimension(300, 400));
        pack();

        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    public void initComponent(Container c) {
        c.setLayout(new BorderLayout());
        c.add(new JLabel("Please select your course:"), BorderLayout.NORTH);

        // OK/Cancel button
        OKButton = new JButton("OK");
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                selectCourse();
            }
        });
        OKButton.setEnabled(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
            }
        });

        JPanel bottomButtons = new JPanel();
        bottomButtons.setLayout(new FlowLayout());
        bottomButtons.add(cancelButton);
        bottomButtons.add(OKButton);

        c.add(bottomButtons, BorderLayout.SOUTH);


        JPanel coursesPanel = new JPanel(new BorderLayout());

        // Load the list of available "courses", or a message to say nope.
        Course currentCourse = Game.getInstance().getCurrentCourse();
        courseListIDs = currentCourse.getAllCoursesId();

        if (courseListIDs.isEmpty()) {
            c.add(new JLabel("No course currently opened, sorry.", JLabel.CENTER), BorderLayout.CENTER);
        } else {
            jListID = new JList(courseListIDs.toArray());
            jListID.setSelectedValue(currentCourse.getCourseId(), true);
            jListID.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent listSelectionEvent) {
                    OKButton.setEnabled(true);
                }
            });

            coursesPanel.add(jListID, BorderLayout.CENTER);
        }

        // Password panel and field
        JLabel passwordLabel = new JLabel("Course password: ");
        passwordField = new JPasswordField(10);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        coursesPanel.add(passwordPanel, BorderLayout.SOUTH);

        c.add(coursesPanel, BorderLayout.CENTER);

    }

    /**
     * Select a new course in the list
     * if a course was already selected, send a leave and heartbeat die signal
     * Launch new heartbeat and join events, change current course
     */
    public void selectCourse() {
        Game game = Game.getInstance();

        // leave the previous course and kill the heartbeat if existing
        if(game.getHeartBeatSpy() != null)
            game.getHeartBeatSpy().die();
        if(game.getCourseID() != null)
            for(ProgressSpyListener spyListener: game.getProgressSpyListeners())
                spyListener.leave();

        // select the new course
        Course course = new CourseAppEngine(jListID.getSelectedValue().toString(),
                new String(passwordField.getPassword()));
        game.setCurrentCourse(course);

        // report the user presence on the server and verify password
        boolean passwordError = false;
        for(ProgressSpyListener spyListener: game.getProgressSpyListeners()){
            String response = spyListener.join();
            if(spyListener instanceof ServerSpy){
                if(ServerAnswer.values()[Integer.parseInt(response)] == ServerAnswer.WRONG_PASSWORD)
                    passwordError = true;
            }
        }

        if(passwordError){
            JOptionPane.showMessageDialog(getParent(), "Wrong module password", "Server error",
                    JOptionPane.ERROR_MESSAGE);
            game.getCurrentCourse().setCourseId(null);
            MainFrame.getInstance().appendToTitle("");
        } else {
            // launch the heartbeat timertask and change window title
            game.setHeartBeatSpy(new HeartBeatSpy(game.getProgressSpyListeners()));
            MainFrame.getInstance().appendToTitle("[ " + jListID.getSelectedValue() + " ]");
        }

        setVisible(false);
    }

    /**
     * Refresh the list of available courses from the server
     */
    public void refreshList(){
       courseListIDs = Game.getInstance().getCurrentCourse().getAllCoursesId();
        jListID.setListData(courseListIDs.toArray());
    }
}
