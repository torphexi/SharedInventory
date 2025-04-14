import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PathfinderDataResourceService } from '../generated/api/pathfinderDataResource.service';
import { PathfinderItem } from '../generated/model/pathfinderItem';
import * as bootstrap from 'bootstrap';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'Pathfinder 2e Shared Inventory';

  inventory: { name: string, quantity: number, details?: PathfinderItem }[] = [];

  money = {
    gold: 100,
    silver: 50,
    copper: 200
  };

  customGold: number = 0;
  customSilver: number = 0;
  customCopper: number = 0;

  itemNames: string[] = [];
  selectedItemDetails: PathfinderItem | null = null;
  selectedItem: PathfinderItem | null = null;
  selectedQuantity: number = 1;
  selectedItemName: string = '';

  dropdownConfig = {
    displayKey: "name",
    search: true,
    height: '250px',
    placeholder: 'Select an item',
    searchPlaceholder: 'Search items...',
    searchOnKey: 'name',
    clearOnSelection: false
  };

  constructor(private pathfinderDataService: PathfinderDataResourceService) {}

  ngOnInit() {
    this.fetchItemNames();
  }

  fetchItemNames() {
    this.pathfinderDataService.apiPathfinderDataNamesGet().subscribe(
      (data) => {
        this.itemNames = data;
      },
      (error) => {
        console.error('Error fetching item names:', error);
      }
    );
  }

  // Methods to manage inventory and money
  addItem(itemName: string, quantity: number) {
    const existingItem = this.inventory.find(item => item.name === itemName);
    if (existingItem) {
      existingItem.quantity += quantity;
    } else {
      this.inventory.push({ name: itemName, quantity });
    }
  }

  removeItem(itemName: string, quantity: number) {
    const existingItem = this.inventory.find(item => item.name === itemName);
    if (existingItem) {
      existingItem.quantity -= quantity;
      if (existingItem.quantity <= 0) {
        this.inventory = this.inventory.filter(item => item.name !== itemName);
      }
    }
  }

  // Add custom amount of money
  addCustomMoney(gold: number, silver: number, copper: number) {
    const totalCopper = this.money.gold * 100 + this.money.silver * 10 + this.money.copper;
    const newTotalCopper = totalCopper + gold * 100 + silver * 10 + copper;

    this.money.gold = Math.floor(newTotalCopper / 100);
    this.money.silver = Math.floor((newTotalCopper % 100) / 10);
    this.money.copper = newTotalCopper % 10;
  }

  // Subtract custom amount of money
  subtractCustomMoney(gold: number, silver: number, copper: number) {
    const totalCopper = this.money.gold * 100 + this.money.silver * 10 + this.money.copper;
    const newTotalCopper = totalCopper - (gold * 100 + silver * 10 + copper);

    if (newTotalCopper < 0) {
      this.money.gold = 0;
      this.money.silver = 0;
      this.money.copper = 0;
    } else {
      this.money.gold = Math.floor(newTotalCopper / 100);
      this.money.silver = Math.floor((newTotalCopper % 100) / 10);
      this.money.copper = newTotalCopper % 10;
    }
  }

  openAddItemModal() {
    const modalElement = document.getElementById('addItemModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  addItemFromModal() {
    if (this.selectedItemName && this.selectedQuantity > 0) {
      this.addItem(this.selectedItemName, this.selectedQuantity);
      this.selectedItemName = '';
      this.selectedQuantity = 1;
    }
  }

  openItemDetailsModal(itemName: string) {
    console.log('Opening modal for item:', itemName);
    this.pathfinderDataService.apiPathfinderDataItemNameGet(itemName).subscribe(
      (data: PathfinderItem) => {
        console.log('Received item details:', data);
        this.selectedItemDetails = data;
        const modalElement = document.getElementById('itemDetailsModal');
        if (modalElement) {
          const modal = new bootstrap.Modal(modalElement);
          modal.show();
        } else {
          console.error('Modal element not found');
        }
      },
      (error) => {
        console.error('Error fetching item details:', error);
      }
    );
  }
}
