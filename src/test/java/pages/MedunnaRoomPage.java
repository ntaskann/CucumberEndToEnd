package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class MedunnaRoomPage {

    public MedunnaRoomPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[text()='Create a new Room']")
    public WebElement newRoomButton;

    @FindBy(xpath = "//input[@id='room-roomNumber']")
    public WebElement roomNumberBox;

    @FindBy(id = "room-status")
    public WebElement statusRadioButton;

    @FindBy(id = "room-price")
    public WebElement priceBox;

    @FindBy(id = "room-roomType")
    public WebElement drapdownRoomTypeButton;

    @FindBy(id = "room-description")
    public WebElement descriptionBox;

    @FindBy(id = "save-entity")
    public WebElement saveButton;

    @FindBy(xpath = "//span[text()='Created Date']")
    public WebElement createdDateButton;

    @FindBy(xpath = "//tr//td[2]")
    public List<WebElement> roomNumberList;

    @FindBy(xpath = "//a[@aria-label='Next']")
    public WebElement nextButton;

    @FindBy(xpath = "//tr")
    public List<WebElement> idSatiri;

//    @FindBy(xpath = "//tr//td[1]")
//    public WebElement idBulmaca;




}
