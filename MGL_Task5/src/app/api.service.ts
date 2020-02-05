import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const localUrl = 'http://localhost:8080/MGL_Task4/fetchAllGames';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getSmartphone() {
    return this.http.get(localUrl);
  }
}
