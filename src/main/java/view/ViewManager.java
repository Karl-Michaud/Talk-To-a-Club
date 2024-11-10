package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(CardLayout cardLayout, JPanel views, ViewManagerModel viewManagerModel) {
        this.cardLayout = cardLayout;
        this.views = views;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);
        }
    }
}
