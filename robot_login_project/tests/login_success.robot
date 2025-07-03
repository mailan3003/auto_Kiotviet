*** Settings ***
Resource    ../resources/login_keywords.robot

*** Test Cases ***
Valid Login Test
    Open Login Page
    Input Credentials    ${VALID_USER}    ${VALID_PASS}
    Wait Until Page Contains    ${SUCCESS_MSG}
    Close Browser
