package interface_adapter.data_access;


import interface_adapter.ViewModel;

/**
 * The ViewModel for the Home View
 */
public class HomeViewModel extends ViewModel<HomeState> {

    public HomeViewModel() {
        super("home");
        setState(new HomeState());
    }



}