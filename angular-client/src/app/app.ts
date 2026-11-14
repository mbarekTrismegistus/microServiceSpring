import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BillingApi } from './billing-api';
import { Bill, Customer, Product } from './models';

type Section = 'invoices' | 'customers' | 'products';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule, CurrencyPipe, DatePipe],
  templateUrl: './app.html'
})
export class AppComponent implements OnInit {
  private readonly api = inject(BillingApi);

  section: Section = 'invoices';
  customers: Customer[] = [];
  products: Product[] = [];
  bills: Bill[] = [];
  customerName = '';
  customerEmail = '';
  productName = '';
  productPrice = 0;
  productQuantity = 1;
  billCustomerId = 0;
  billProductId = 0;
  billItemQuantity = 1;
  draftItems: Array<{ productId: number; productName: string; unitPrice: number; quantity: number }> = [];
  errorMessage = '';
  successMessage = '';

  ngOnInit(): void {
    this.refreshCustomers();
    this.refreshProducts();
    this.refreshBills();
  }

  selectSection(section: Section): void {
    this.section = section;
    this.errorMessage = '';
    this.successMessage = '';
  }

  refreshCustomers(): void {
    this.api.customers().subscribe({
      next: customers => {
        this.customers = customers;
        if (!this.billCustomerId && customers.length) this.billCustomerId = customers[0].id;
      },
      error: error => this.showError(error)
    });
  }

  refreshProducts(): void {
    this.api.products().subscribe({
      next: products => {
        this.products = products;
        if (!this.billProductId && products.length) this.billProductId = products[0].id;
      },
      error: error => this.showError(error)
    });
  }

  refreshBills(): void {
    this.api.bills().subscribe({
      next: bills => this.bills = bills,
      error: error => this.showError(error)
    });
  }

  createCustomer(): void {
    this.clearMessages();
    this.api.createCustomer({ name: this.customerName.trim(), email: this.customerEmail.trim() }).subscribe({
      next: customer => {
        this.customers = [...this.customers, customer];
        if (!this.billCustomerId) this.billCustomerId = customer.id;
        this.customerName = '';
        this.customerEmail = '';
        this.successMessage = 'Customer added.';
      },
      error: error => this.showError(error)
    });
  }

  createProduct(): void {
    this.clearMessages();
    this.api.createProduct({
      name: this.productName.trim(),
      price: this.productPrice,
      quantity: this.productQuantity
    }).subscribe({
      next: product => {
        this.products = [...this.products, product];
        if (!this.billProductId) this.billProductId = product.id;
        this.productName = '';
        this.productPrice = 0;
        this.productQuantity = 1;
        this.successMessage = 'Product added.';
      },
      error: error => this.showError(error)
    });
  }

  addBillItem(): void {
    const product = this.products.find(candidate => candidate.id === Number(this.billProductId));
    if (!product || this.billItemQuantity < 1) return;
    this.draftItems = [...this.draftItems, {
      productId: product.id,
      productName: product.name,
      unitPrice: product.price,
      quantity: this.billItemQuantity
    }];
  }

  removeBillItem(index: number): void {
    this.draftItems = this.draftItems.filter((_, itemIndex) => itemIndex !== index);
  }

  createBill(): void {
    if (!this.billCustomerId || !this.draftItems.length) return;
    this.clearMessages();
    this.api.createBill({
      customerId: Number(this.billCustomerId),
      items: this.draftItems.map(({ productId, quantity }) => ({ productId, quantity }))
    }).subscribe({
      next: bill => {
        this.bills = [bill, ...this.bills];
        this.draftItems = [];
        this.successMessage = `Invoice #${bill.id} created.`;
      },
      error: error => this.showError(error)
    });
  }

  billTotal(bill: Bill): number {
    return bill.items.reduce((total, item) => total + item.unitPrice * item.quantity, 0);
  }

  draftTotal(): number {
    return this.draftItems.reduce((total, item) => total + item.unitPrice * item.quantity, 0);
  }

  private clearMessages(): void {
    this.errorMessage = '';
    this.successMessage = '';
  }

  private showError(_error: unknown): void {
    this.errorMessage = 'The API is unavailable. Start the services and try again.';
  }
}
