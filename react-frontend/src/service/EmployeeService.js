import axios from 'axios';
const EMPLOYEE_SERVICE_BASE_URL="http://localhost:9191/employees";
const EMPLOYEE_ID=2
class EmployeeService{
    getEmployee(){
        return axios.get(EMPLOYEE_SERVICE_BASE_URL + '/' + EMPLOYEE_ID);
    }

}
export default new EmployeeService
