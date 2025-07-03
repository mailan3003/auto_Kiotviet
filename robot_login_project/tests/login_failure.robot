*** Settings ***
Resource    ../resources/login_keywords.robot

*** Test Cases ***
Invalid Login Test
    Open Login Page
    Input Credentials    ${INVALID_USER}    ${INVALID_PASS}
    Wait Until Page Contains    ${ERROR_MSG}
    Close Browser
