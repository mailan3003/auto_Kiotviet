*** Settings ***
Resource    ../resources/login_keywords.robot

*** Test Cases ***
Invalid Login Test
    Open Login Page
    Input Credentials    ${INVALID_USER}    ${INVALID_PASS}
    Wait Until Page Contains    ${LOGIN_ERROR_MGS}   timeout=5s
    Page Should Contain    ${LOGIN_ERROR_MGS}
    Close Browser
