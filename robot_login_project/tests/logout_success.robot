*** Settings ***
Resource    ../resources/login_keywords.robot

*** Test Cases ***
Logout Test After Successful Login
    Open Login Page
    Input Credentials    ${VALID_USER}    ${VALID_PASS}
    Wait Until Page Contains    ${SUCCESS_MSG}
    Click Logout
    Wait Until Page Contains    Login Page
    Close Browser
