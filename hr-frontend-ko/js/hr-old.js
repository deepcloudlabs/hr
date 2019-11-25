class Employee {
    constructor(){
        this.identityNo = ko.observable("");
        this.fullname = ko.observable("");
        this.iban = ko.observable("");
        this.salary = ko.observable("");
        this.birthYear = ko.observable("");
        this.fulltime = ko.observable(true);
        this.department = ko.observable("IT");
        this.photo = ko.observable(AppConfig.NO_IMAGE);
    }
};
class HrViewModel {
    constructor(){
        this.employee = new Employee();
        this.employees = ko.observableArray([]);
        this.departments = ko.observableArray(["Sales", "Finance", "IT", "HR"]);
        this.retrieveAll = this.retrieveAll.bind(this);
        this.addEmployee = this.addEmployee.bind(this);
    }
    addEmployee(){
        $.ajax({
            "method": "POST",
            "url": "http://localhost:4001/employees",
            "contentType": "application/json",
            "data": ko.toJSON(this.employee),
            "success": (status) => {
                this.retrieveAll();
            }
        })
    }
    retrieveAll(){
        $.ajax({
            "method": "GET",
            "url": "http://localhost:4001/employees",
            "success": (employees) => {
                this.employees(employees);
            }
        })
    }
};