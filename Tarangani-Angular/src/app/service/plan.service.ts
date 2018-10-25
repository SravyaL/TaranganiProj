import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, Response } from '@angular/http';
import { Plan } from '../model/plan';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  baseUrl: string;

  constructor(private http: Http) {
    this.baseUrl = "http://localhost:2020/plans";
   }

   getBaseUrlBypTitle(pTitle: string): string {
    return this.baseUrl + "/" + pTitle;
  }

  getSearchUrl(field: string, value: string): string {
    return this.baseUrl + "/" + field + "/" + value;
  }

  getJsonContentTypeHeader(): RequestOptions {
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    return new RequestOptions({ headers: headers });
  }

  getAllPlans(): Observable<Plan[]> {
    return this.http.get(this.baseUrl).pipe(
      map(data => data.json())
    );
  }

  searchPlans(field: string, value: string): Observable<Plan[]> {
    return this.http.get(this.getSearchUrl(field,value)).pipe(
      map(data => data.json())
    );
  }

  getPlanBypTitle(pTitle: string): Observable<Plan> {
    return this.http.get(this.getBaseUrlBypTitle(pTitle)).pipe(
      map(data => data.json())
    );
  }

 


}
