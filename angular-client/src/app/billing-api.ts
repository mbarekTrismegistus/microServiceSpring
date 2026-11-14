import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Bill, CreateBill, Customer, Product } from './models';

@Injectable({ providedIn: 'root' })
export class BillingApi {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = 'http://localhost:8888';

  customers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.baseUrl}/customers`);
  }

  createCustomer(customer: Pick<Customer, 'name' | 'email'>): Observable<Customer> {
    return this.http.post<Customer>(`${this.baseUrl}/customers`, customer);
  }

  products(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/products`);
  }

  createProduct(product: Omit<Product, 'id'>): Observable<Product> {
    return this.http.post<Product>(`${this.baseUrl}/products`, product);
  }

  bills(): Observable<Bill[]> {
    return this.http.get<Bill[]>(`${this.baseUrl}/bills`);
  }

  createBill(bill: CreateBill): Observable<Bill> {
    return this.http.post<Bill>(`${this.baseUrl}/bills`, bill);
  }
}
