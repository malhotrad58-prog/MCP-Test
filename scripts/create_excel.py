from openpyxl import Workbook

wb = Workbook()
ws = wb.active
ws.title = 'LoginData'
ws.append(['URL', 'USERNAME', 'PASSWORD', 'SCENARIO', 'EXPECTED_MESSAGE'])
ws.append(['https://www.saucedemo.com/', 'standard_user', 'secret_sauce', 'VALID', ''])
ws.append(['https://www.saucedemo.com/', 'wrong_user', 'wrong_password', 'INVALID', 'do not match any user'])
ws.append(['https://www.saucedemo.com/', '', 'secret_sauce', 'MISSING_USERNAME', 'Username is required'])

wb.save('src/test/resources/login_credentials.xlsx')
print('Created src/test/resources/login_credentials.xlsx')
