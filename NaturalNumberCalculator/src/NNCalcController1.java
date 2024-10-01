import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Put your name here
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        NaturalNumber topNum = new NaturalNumber2(0);
        NaturalNumber botNum = new NaturalNumber2(0);
        topNum = model.top();
        botNum = model.bottom();

        if (botNum.compareTo(topNum) > 0) {
            view.updateSubtractAllowed(false);
        } else {
            view.updateSubtractAllowed(true);
        }
        if (botNum.compareTo(TWO) >= 0 && botNum.compareTo(INT_LIMIT) <= 0) {
            view.updateRootAllowed(true);
        } else {
            view.updateRootAllowed(false);
        }
        if (botNum.isZero()) {
            view.updateDivideAllowed(false);
        } else {
            view.updateDivideAllowed(true);
        }
        if (botNum.compareTo(INT_LIMIT) <= 0) {
            view.updatePowerAllowed(true);
        } else {
            view.updatePowerAllowed(false);
        }

        view.updateBottomDisplay(botNum);
        view.updateTopDisplay(topNum);
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        NaturalNumber botNum = new NaturalNumber2(0);
        NaturalNumber topNum = new NaturalNumber2(0);
        botNum = this.model.bottom();
        topNum = this.model.top();

        topNum.copyFrom(botNum);

        this.updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {

        NaturalNumber botNum = new NaturalNumber2(0);
        NaturalNumber topNum = new NaturalNumber2(0);
        botNum = this.model.bottom();
        topNum = this.model.top();
        botNum.add(topNum);
        topNum.clear();
        this.updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processSubtractEvent() {

        NaturalNumber botNum = new NaturalNumber2(0);
        NaturalNumber topNum = new NaturalNumber2(0);
        botNum = this.model.bottom();
        topNum = this.model.top();
        topNum.subtract(botNum);
        botNum.copyFrom(topNum);
        topNum.clear();
        this.updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processMultiplyEvent() {

        NaturalNumber botNum = new NaturalNumber2(0);
        NaturalNumber topNum = new NaturalNumber2(0);
        botNum = this.model.bottom();
        topNum = this.model.top();
        topNum.multiply(botNum);
        botNum.copyFrom(topNum);
        topNum.clear();
        this.updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {
        NaturalNumber botNum = new NaturalNumber2(0);
        NaturalNumber topNum = new NaturalNumber2(0);
        NaturalNumber remainder = new NaturalNumber2(0);
        botNum = this.model.bottom();
        topNum = this.model.top();
        remainder = topNum.divide(botNum);
        botNum.transferFrom(topNum);
        topNum.transferFrom(remainder);
        this.updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        NaturalNumber botNum = new NaturalNumber2(0);
        NaturalNumber topNum = new NaturalNumber2(0);
        botNum = this.model.bottom();
        topNum = this.model.top();
        topNum.power(botNum.toInt());
        botNum.transferFrom(topNum);
        this.updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        NaturalNumber botNum = new NaturalNumber2(0);
        NaturalNumber topNum = new NaturalNumber2(0);
        botNum = this.model.bottom();
        topNum = this.model.top();
        topNum.root(botNum.toInt());
        botNum.transferFrom(topNum);
        this.updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        NaturalNumber botNum = new NaturalNumber2(0);
        botNum = this.model.bottom();
        botNum.multiplyBy10(digit);
        this.updateViewToMatchModel(this.model, this.view);

    }

}
