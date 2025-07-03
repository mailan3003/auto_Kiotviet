*** Settings ***
Resource    ../resources/login_keywords.robot

*** Test Cases ***
Valid Login Test
    Open Login Page
    Input Credentials    ${VALID_USER}    ${VALID_PASS}
    Wait Until Location Contains    /DashBoard    timeout=10s
    Location Should Be    ${DASHBOARD_URL}
    Close Browser
