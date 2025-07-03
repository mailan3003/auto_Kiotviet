*** Settings ***
Library    SeleniumLibrary
Resource  ../variables/login_variables.robot

*** Keywords ***
Open Login Page
    Open Browser    ${URL}    ${BROWSER} 
    Maximize Browser Window
    Wait Until Element Is Visible    id=username

Input Credentials
    [Arguments]    ${username}    ${password}
    Input Text     id=username    ${username}
    Input Text     id=password    ${password}
    Click Button   css=button[type="submit"]

Click Logout
    Click Element    css=a[href="/logout"]
    Wait Until Page Contains    Login Page

Close Browser
    Run Keyword And Ignore Error    Close Browser
