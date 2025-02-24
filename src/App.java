
import interfaces.iEVDContract;
import model.*;
import presenter.Presenter;
import view.*;

public class App {
    public static void main(String[] args) {
        iEVDContract.Presenter presenter = new Presenter();
        iEVDContract.Model model = new Simulator();
        iEVDContract.View view = new View();
        model.setPresenter(presenter);
        view.setPresenter(presenter);
        presenter.setModel(model);
        presenter.setView(view);
    }
}
