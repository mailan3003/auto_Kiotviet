*** Settings ***
Library    SeleniumLibrary
Resource    ../resources/login_keywords.robot

*** Test Cases ***
Valid Logout Test
    Open Login Page
    Input Credentials    ${VALID_USER}    ${VALID_PASS}
    Wait Until Location Contains    /DashBoard    timeout=10s
    Wait Until Page Contains Element    css=.vodal-mask    timeout=5s
    Close Vodal Overlay If Present
    Wait Until Location Is    ${DASHBOARD_URL}    timeout=10s
    Click Admin
    Click Logout
    Wait Until Location Contains    /login    timeout=10s
    Location Should Be    ${URL}
    Close Browser
