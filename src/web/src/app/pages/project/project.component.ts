
import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { ProjectService } from 'src/app/services/shared/project.service';
import { Page } from 'src/app/common/page';
import { BsModalRef, BsModalService } from 'ngx-bootstrap';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ConfirmationComponent } from 'src/app/shared/confirmation/confirmation.component';
import { UserService } from 'src/app/services/shared/user.service';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
page = new Page();
modalRef: BsModalRef;
projectForm :FormGroup;
@ViewChild('tplProjectDeleteCell') tplProjectDeleteCell: TemplateRef<any>;

 colonnes=[];
 rows=[];
 managerOptions=[];

  constructor(private projectService :ProjectService,
             private modalService: BsModalService,
             private formBuilder: FormBuilder,
             private userService: UserService) { 
  }

  ngOnInit() {
this.colonnes=[
      {prop:'id',name:'Numero'},
      {prop:'projectName',name:'Titre',sortable:false}, 
      {prop:'projectCode',name:'Code Liste',sortable:false},
      {prop:'manager.nameSurname',name:'Occasion',sortable:false},
      {prop:'id',name:'Actions',cellTemplate: this.tplProjectDeleteCell ,flexGrow:1,sortable:false}
      ];
this.setPage({offset:0}); 

  this.projectForm = this.formBuilder.group({
    projectCode: [null, [Validators.required, Validators.minLength(2), Validators.maxLength(8)]],
    projectName: [null, [Validators.required, Validators.minLength(4)]],
    managerId: [null, [Validators.required]],
  });

this.userService.getAll().subscribe(res => {
  this.managerOptions= res;
  console.log(res);
  });

  }
get fo(){ return this.projectForm.controls}

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }


  closeAndResetModel(){
    this.projectForm.reset();
    this.modalRef.hide();
  }

  saveProject(){
    debugger

  if(!this.projectForm.valid)
  return;

  this.projectService.createProject(this.projectForm.value).subscribe(
    response => {
      this.setPage({ offset:0 }); 
      this.closeAndResetModel();
    }
  )
  
  }

  setPage(pageInfo){
    this.page.page=pageInfo.offset;
    this.projectService.getAllPageable(this.page).subscribe(pageData =>{
      this.page.size =pageData.size;
      this.page.page =pageData.page;
      this.page.totalElements= pageData.totalElements;
      this.rows=pageData.content;
    })

  }
  showProjectDeleteConf(value){
    const modal = this.modalService.show(ConfirmationComponent);
    (<ConfirmationComponent>modal.content).showConfirmation('Confirmation de suppression','Etes-vous sûr de vouloir supprimer?');
    (<ConfirmationComponent>modal.content).onClose.subscribe(
      result=>{

        if(result===true){
          this.projectService.delete(value).subscribe(response =>{
            if(response===true)
            this.setPage({ offset:0 });
             //console.log("Yes")
          });
 
        }else if(result===false)
        console.log("No")
      }
    );
  }


}
