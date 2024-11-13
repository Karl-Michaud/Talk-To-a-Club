package interface_adapter.home;


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