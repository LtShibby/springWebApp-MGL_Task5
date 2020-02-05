import { Component, OnInit, NgZone } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigationBar',
  templateUrl: './navigationBar.component.html',
  styleUrls: ['./navigationBar.component.css']
})
export class NavigationBarComponent implements OnInit {

  constructor(    
    private ngZone: NgZone,
    private router: Router
    ) { }

  ngOnInit() {
  }


  routeToGamePage(){
    this.ngZone.run(() => this.router.navigateByUrl('/game'))
    }

}
