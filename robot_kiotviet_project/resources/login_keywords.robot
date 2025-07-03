*** Settings ***
Library    SeleniumLibrary
Resource  ../variables/login_variables.robot
Library    SeleniumLibrary    screenshot_on_failure=False

*** Keywords ***
Open Login Page
    Open Browser    ${URL}    ${BROWSER} 
    Maximize Browser Window
    Wait Until Element Is Visible    id=UserName    timeout=10s

Input Credentials
    [Arguments]    ${username}    ${password}
    Input Text     id=UserName    ${username}
    Input Text     id=Password    ${password}
    Click Button    name=quan-ly
    
Close Vodal Overlay If Present
    ${exists}=    Run Keyword And Return Status    Element Should Be Visible    css=.vodal-close
    Run Keyword If    ${exists}    Execute Javascript    document.querySelector(".vodal-close").click()
    Sleep    2s
    Run Keyword And Ignore Error    Wait Until Element Does Not Exist    css=.vodal-mask    10s

Click Admin
    Wait Until Element Is Visible    xpath=//a[@title="admin"]    timeout=10s
    Click Element    xpath=//a[@title="admin"]


Click Logout
    Wait Until Element Is Visible    xpath=//span[normalize-space(.)="Đăng xuất"]    timeout=5s
    Click Element    xpath=//span[normalize-space(.)="Đăng xuất"]

Close Browser
    Run Keyword And Ignore Error    Close Browser
