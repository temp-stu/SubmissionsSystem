/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.pecinovsky.assev.library.gui;

import cz.pecinovsky.assev.library.AssignmentDescriptor;
import cz.pecinovsky.assev.library.LibraryContext;
import cz.pecinovsky.assev.library.SolutionDescriptor;
import cz.pecinovsky.assev.library.connection.ServerConnection;
import cz.pecinovsky.assev.library.ui.Evidence;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import static javax.swing.text.DefaultCaret.ALWAYS_UPDATE;

/**
 *
 * @author Petr
 */
public class Gui {

    private Evidence evidence;
    private JFrame frame;
    private JPanel textPenel;
    private JPanel buttonPanel;
    private JTextArea textArea;
    private JButton uploadResultButton;
    private JButton getUkolyToTestButton;
    private JButton testWorksButton;
    private static LibraryContext api = LibraryContext.getInstance();

    private class Download implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            ArrayList<AssignmentDescriptor> asigDesc = api.getAssignmentDescriptors();
            //TO DO with asignement desciprors (popisy zadání)
            ArrayList<SolutionDescriptor> solutionDesc = api.getSolutionDescriptor(asigDesc.get(6));
            //TO Do with solution descriptors (popisy zadání) - toto obdrží arraylist všech domácích úkolů (řešení) dle danoho asignementdesriptoru, ale pouze neohodnocené
            //všechny zde ->  ArrayList<SolutionDescriptor> solutionDesc = api.getSolutionDescriptor(false,asigDesc.get(0));
            textArea.append("přidáno úkolů "+solutionDesc.size()+"\n");
        }
    }

    private class TestWorks implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {            
            textArea.append("Testing homeworks TO DO yourself :-D!\n");
        }
    }

    private class Upload implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            //pošlu lokální JAVA aplikaci výsledek jednoho testu
            api.sendEvaluation("3", 0.01,"Všechno úplně blbě :-D");
            textArea.append("Uploading homeworks finished!\n");
        }
    }

    public Gui() {
        evidence = new Evidence();
        init();
    }

    private void init() {
        frame = new JFrame("Homework tester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocation(150, 250);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        frame.setResizable(false);

        textPenel = new JPanel();
        buttonPanel = new JPanel();

        frame.add(textPenel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.NORTH);

        initTab();
        initButtons();
        frame.pack();
    }

    private void initTab() {
        textArea = new JTextArea(8, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textPenel.add(scrollPane);
    }

    private void initButtons() {
        getUkolyToTestButton = new JButton("Stáhni úkoly");
        getUkolyToTestButton.setVisible(true);
        buttonPanel.add(getUkolyToTestButton);
        getUkolyToTestButton.addActionListener(new Download());

        testWorksButton = new JButton("Otestuj úkoly");
        testWorksButton.setVisible(true);
        buttonPanel.add(testWorksButton);
        testWorksButton.addActionListener(new TestWorks());

        uploadResultButton = new JButton("Ulož výsledky");
        uploadResultButton.setVisible(true);
        buttonPanel.add(uploadResultButton);
        uploadResultButton.addActionListener(new Upload());
    }

}
