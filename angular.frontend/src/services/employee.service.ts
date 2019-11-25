import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "./employee";
import {HrResponse} from "./hrresponse";

@Injectable()
export class EmployeeService {
  private baseUrl: string = "http://localhost:7001";

  constructor(private http: HttpClient) {
    console.log("EmployeeService is initialized.")
  }

  findAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.baseUrl.concat("/employees"));
  }

  findEmployee(identityNo: string): Observable<Employee> {
    let requestUrl = this.baseUrl.concat("/employees/")
      .concat(identityNo);
    return this.http.get<Employee>(requestUrl);
  }

  removeEmployee(identityNo: string): Observable<Employee> {
    let requestUrl = this.baseUrl.concat("/employees/").concat(identityNo);
    return this.http.delete<Employee>(requestUrl);
  }

  addEmployee(emp: Employee): Observable<HrResponse> {
    let requestUrl = this.baseUrl.concat("/employees/");
    return this.http.post<HrResponse>(requestUrl, emp);
  }

  updateEmployee(emp: Employee): Observable<HrResponse> {
    let requestUrl = this.baseUrl.concat("/employees/");
    return this.http.put<HrResponse>(requestUrl, emp);
  }

}
