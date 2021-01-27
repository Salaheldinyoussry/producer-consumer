<template>
  <v-app class="black">
    <navbar />
    <v-content dark class="blue accent-1 elevation-18">
      <v-card style="margin-left:200px; margin-top:50px; width:1700px">
        <br />
        <br />
        <div style="margin-left:100px">
          <v-text-field
            label="Machine Or Queue Name"
            style="width:250px; margin-left:20px;"
            v-model="a"
          ></v-text-field>
          <h3 style="margin-left:20px">To</h3>
          <v-text-field
            label="Queue Or Machine Name"
            style="width:250px; margin-left:20px;"
            v-model="b"
          ></v-text-field>
          <v-text-field
            label="products Number"
            style="width:250px; margin-left:20px;"
            v-model="productNo"
          ></v-text-field>
          <v-btn style="margin-left:20px" @click="connect()">Connect</v-btn>
          <v-btn
            :class="sbutton"
            style="margin-left:40px; margin-right:20px"
            @click="addMach()"
            >add Machine</v-btn
          >
          <v-btn :class="sbutton1" @click="addQueue()">add Queue</v-btn>
          <v-btn style="margin-left:20px" @click="newSim()"
            >new simulation</v-btn
          >

          <v-btn
            class="ma-2"
            :loading="loading2"
            :disabled="loading2"
            color="success"
            @click="simulate()"
          >
            Run simulation
            <template v-slot:loader>
              <span>simulating...</span>
            </template>
          </v-btn>
          <v-btn
            class="ma-2"
            :loading="loading3"
            :disabled="loading3"
            color="success"
            @click="replay()"
          >
            replay simulation
            <template v-slot:loader>
              <span>simulating...</span>
            </template>
          </v-btn>
          <!-- <v-btn @click="tt">Prepare Simulation</v-btn> -->
        </div>

        <!-- <v-card hover style="width:400px; margin-left:20px; margin-inline">
            <v-list flat>
              <v-subheader>Products</v-subheader>
              <v-list-item-group color="primary">
                <v-list-item v-for="(item, i) in items" :key="i">
                  <v-list-item-content>
                    <v-list-item-title :style="pclass">{{
                      item.text
                    }}</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
            <br />

            <v-btn
              class="b"
              color="orange lighten-2"
              text
              @click="addp()"
              style="margin-bottom:20px"
            >
              add
            </v-btn>
            <input
              id="colorPick"
              type="color"
              v-model="pcolor"
              @change="changeColor()"
            />
          </v-card> -->
        <br />
        <br />

        <canvas
          id="my-canvas"
          width="500"
          height="500"
          style="margin-left:100px"
        ></canvas>
        <br />
        <br />
      </v-card>
      <br />
      <br />
      <br />
    </v-content>
  </v-app>
</template>

<script>
import navbar from "./components/navbar";
import axios from "axios";
// let savedImageData;

export default {
  name: "app",
  components: {
    navbar,
  },
  data: function() {
    return {
      productNo: 5,
      prepare: false,
      loading2: false,
      loading3: false,
      sbutton: "blue",
      sbutton1: "white",
      canvas: null,
      ctx: null,
      mx: -1,
      my: -1,
      items: [],
      addM: true,
      color: "green",
      mcounter: 1,
      qcounter: 0,
      shapes: [],
      arrows: [],
      pcolor: "black",
      a: "",
      b: "",
      innerWidth: 1600,
      innerHeight: 700,
      pnumber: 0,
      sessionKey: -1,
      backMachines: [],
      backQueues: [],
      machIn: [],
      machOut: [],
      mmm: [],
      globalF: 0,
      simulationWas: false,
      sessionKeysim: 0,

      pclass: {
        color: "yellow",
      },
    };
  },
  methods: {
    newSim() {
      // if(this.loading2 || this.loading3){

      //}
      location.reload();

      // this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
      // this.shapes = [];
      // this.mm = [];
      // this.pnumber = 0;
      // this.backMachines = [];
      // this.backQueues = [];
      // this.machIn = [];
      // this.machOut = [];
      // this.mmm = [];
      // this.arrows = [];
      // this.mcounter = 1;
      // this.qcounter = 0;
      // this.globalF = 0;
      // this.simulationWas = false;
      // this.productNo = 5;
      // window.assign.locatio
      // if (!this.isNumeric(this.productNo)) {
      //   alert("number of products must be numeric");
      // } else {
      //   this.loading2 = true;
      //   var pn = parseInt(this.productNo);
      //   var qn = this.shapes.length - this.machIn.length;
      //   var mn = this.machIn.length;
      //   var x = {
      //     machineIn: [],
      //     machineOut: [],
      //     productNo: 0,
      //     queueNo: 0,
      //     machineNo: 0,
      //   };
      //   x.machineIn = this.machIn;
      //   x.machineOut = this.machOut;
      //   x.productNo = pn;
      //   x.queueNo = qn;
      //   x.machineNo = mn;
      //   this.simulationWas = true;

      //   axios
      //     .post("http://localhost:8091/startSim", x)
      //     .then(() => {
      //       //this.sessionKey = res.data;
      //       //console.log(this.t);
      //     })
      //     .catch((error) => {
      //       this.errorMessage = error.message;
      //       console.error("There was an error!", error);
      //     });
      //   // window.setTimeout(this.continueUpdate, 50);
      //   this.newUpdate();
      // }
    },
    // async newUpdate() {
    //   var flag = false;
    //   while (!flag && this.globalF != 1) {
    //     console.log("hey iam tired");
    //     await axios
    //       .get("http://localhost:8091/getUpdate")
    //       .then((res) => {
    //         //this.t = res.data;
    //         // console.log(res.data.queueProductNo);
    //         var q = res.data.queueProductNo;
    //         var m = res.data.MachineCurrentProduct;
    //         var lfl = res.data.finished;
    //         this.globalF = res.data.finished;
    //         if (lfl == 1) {
    //           flag = true;
    //           this.loading2 = false;
    //           this.finishsimupdate();
    //         }
    //         // console.log(m);
    //         //console.log(q);

    //         this.mmm = m;
    //         var i;
    //         for (i = 0; i < this.shapes.length; i++) {
    //           var no = parseInt(
    //             this.shapes[i].text.substring(1, this.shapes.length)
    //           );
    //           if (this.shapes[i].text.charAt(0) == "Q") {
    //             this.shapes[i].number = q[no];
    //           }
    //           //      var x = { text: "", color: "", x: 0, y: 0, flash: false, number: 0 };
    //           else {
    //             // console.log(m);
    //             if (m[no - 1] == "empty") {
    //               this.shapes[i].color = "green";
    //             } else {
    //               //this.shapes[i].color = m[no - 1];
    //               //console.log(this.shapes[i].color);

    //               if (this.shapes[i].color != m[no - 1]) {
    //                 this.shapes[i].flash == true;
    //                 this.shapes[i].color = m[no - 1];
    //               }
    //               this.shapes[i].color == m[no - 1];
    //             }
    //           }
    //         }
    //         this.redrawSim();
    //       })
    //       .catch((error) => {
    //         this.errorMessage = error.message;
    //         console.error("There was an error!", error);
    //       });

    //     if (flag) {
    //       this.loading2 = false;
    //       this.finishsimupdate();
    //       break;
    //     }
    //   }
    // },

    tt() {
      // this.loading2 = !this.loading2;
      this.prepare = true;
      this.ctx.clear;
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
      this.pnumber = Math.max(Math.floor(Math.random() * 21), 1);
      var i;
      for (i = 0; i < this.shapes.length; i++) {
        if (this.shapes[i].text == "Q0") {
          this.shapes[i].number = this.pnumber;
        }
        this.redrawSim();
      }
      axios
        .get(
          "http://localhost:8090/prepareSim/" +
            this.sessionKeysim +
            "/" +
            this.pnumber
        )
        .then((res) => {
          this.sessionKey = res.data;
          console.log(this.t);
        })
        .catch((error) => {
          this.errorMessage = error.message;
          console.error("There was an error!", error);
        });
    },
    // setProduct(){

    // },
    isNumeric(value) {
      return /^\d+$/.test(value);
    },
    simulate() {
      if (this.simulationWas) {
        alert("simulation finished you can replay or start new one");
      }
      var lolo = false;
      var j = 0;
      if (this.machIn.length == 0) {
        lolo = true;
      }
      for (j = 0; j < this.machIn.length; j++) {
        if (this.machIn[j] == -1 || this.machOut[j] == -1) {
          alert("Machine or Queue not Connected!!!");
          lolo = true;
          break;
        }
        if (this.machOut[j] == 0) {
          alert("Q0 is an input Queue!!!");
          lolo = true;
          break;
        }
      }
      var lgn = this.shapes.length - this.machIn.length;
      var t = 0;
      var fflog = true;
      for (t = 0; t < lgn; t++) {
        fflog = false;
        var w = 0;
        for (w = 0; w < this.machIn.length; w++) {
          if (this.machIn[w] == t) {
            fflog = true;
            break;
          }
        }
        var vb = 0;
        for (vb = 0; vb < this.machOut.length; vb++) {
          if (this.machOut[vb] == t) {
            fflog = true;
            break;
          }
        }
        if (!fflog) {
          console.log(t);
          lolo = true;
          alert("invalid input");
        }
      }

      if (lolo && this.loading2 && this.loading3) {
        console.log("lolo");
      } else if (
        !this.isNumeric(this.productNo) &&
        !this.loading2 &&
        lolo != true
      ) {
        alert("number of products must be numeric");
      } else if (
        !lolo &&
        !this.loading2 &&
        !this.loading3 &&
        !this.simulationWas
      ) {
        this.loading2 = true;
        var pn = parseInt(this.productNo);
        var qn = this.shapes.length - this.machIn.length;
        var mn = this.machIn.length;
        var x = {
          machineIn: [],
          machineOut: [],
          productNo: 0,
          queueNo: 0,
          machineNo: 0,
        };
        x.machineIn = this.machIn;
        x.machineOut = this.machOut;
        x.productNo = pn;
        x.queueNo = qn;
        x.machineNo = mn;
        this.simulationWas = true;

        axios
          .post("http://localhost:8091/startSim/" + this.sessionKeysim, x)
          .then(() => {
            //this.sessionKey = res.data;
            //console.log(this.t);
          })
          .catch((error) => {
            this.errorMessage = error.message;
            console.error("There was an error!", error);
          });
        window.setTimeout(this.continueUpdate, 50);
        // this.continueUpdate();
      }
    },
    async continueUpdate() {
      var flag = false;
      while (!flag && this.globalF != 1) {
        //  console.log("hey iam tired");
        await axios
          .get("http://localhost:8091/getUpdate/" + this.sessionKeysim)
          .then((res) => {
            //this.t = res.data;
            // console.log(res.data.queueProductNo);
            var q = res.data.queueProductNo;
            var m = res.data.MachineCurrentProduct;
            var lfl = res.data.finished;
            this.globalF = res.data.finished;
            if (lfl == 1) {
              flag = true;
              this.loading2 = false;
              this.finishsimupdate();
            }
            // console.log(m);
            //console.log(q);

            this.mmm = m;
            var i;
            for (i = 0; i < this.shapes.length; i++) {
              var no = parseInt(
                this.shapes[i].text.substring(1, this.shapes.length)
              );
              if (this.shapes[i].text.charAt(0) == "Q") {
                this.shapes[i].number = q[no];
              }
              //      var x = { text: "", color: "", x: 0, y: 0, flash: false, number: 0 };
              else {
                // console.log(m);
                if (m[no - 1] == "empty") {
                  this.shapes[i].color = "green";
                } else {
                  //this.shapes[i].color = m[no - 1];
                  //console.log(this.shapes[i].color);

                  if (this.shapes[i].color != m[no - 1]) {
                    // window.setInterval(function() {
                    //   this.ctx.fillStyle = "blue";
                    //   this.ctx.fillRect(10, 10, 80, 80);
                    // }, 110000);
                    this.shapes[i].flash = true;
                    this.shapes[i].color = m[no - 1];
                    //var sd;
                    // for (sd = 0; sd < 100000000; sd++) {
                    //   var ruou = 100;
                    // }
                    //   console.log("man ft");
                    // window.setTimeout(this.redrawSim, 1000);

                    // console.log(ruou);
                  }
                  this.shapes[i].color = m[no - 1];
                }
              }
            }
            this.redrawSim();
          })
          .catch((error) => {
            this.errorMessage = error.message;
            console.error("There was an error!", error);
          });

        if (flag) {
          this.loading2 = false;
          this.finishsimupdate();
          break;
        }
      }
    },
    replay() {
      if (this.simulationWas && !this.loading2) {
        axios
          .get("http://localhost:8091/replay/" + this.sessionKeysim)
          .then({});
        this.loading3 = true;
        this.replayUpdate();
      } else {
        alert("you have to run a simulation first");
      }
    },
    async replayUpdate() {
      console.log("replay");
      var flag = false;
      while (!flag) {
        await axios
          .get("http://localhost:8091/getReplayUpdate/" + this.sessionKeysim)
          .then((res) => {
            //this.t = res.data;
            // console.log(res.data.queueProductNo);
            var q = res.data.queueProductNo;
            var m = res.data.MachineCurrentProduct;
            var lfl = res.data.finished;
            this.globalF = res.data.finished;
            if (lfl == 1) {
              flag = true;
              this.loading3 = false;
              this.finishsimupdate();
            }

            this.mmm = m;
            var i;
            for (i = 0; i < this.shapes.length; i++) {
              var no = parseInt(
                this.shapes[i].text.substring(1, this.shapes.length)
              );
              if (this.shapes[i].text.charAt(0) == "Q") {
                this.shapes[i].number = q[no];
              }
              //      var x = { text: "", color: "", x: 0, y: 0, flash: false, number: 0 };
              else {
                // console.log(m);
                if (m[no - 1] == "empty") {
                  this.shapes[i].color = "green";
                } else {
                  // this.shapes[i].color = m[no - 1];

                  if (this.shapes[i].color != m[no - 1]) {
                    this.shapes[i].flash = true;
                    this.shapes[i].color = m[no - 1];
                  }
                  this.shapes[i].color = m[no - 1];
                }
              }
            }
            this.redrawSim();
          })
          .catch((error) => {
            this.errorMessage = error.message;
            console.error("There was an error!", error);
          });

        if (flag) {
          this.loading3 = false;
          this.finishsimupdate();
          break;
        }
      }
    },
    finishsimupdate() {
      var i;
      for (i = 0; i < this.shapes.length; i++) {
        if (this.shapes[i].text.charAt(0) == "M") {
          this.shapes[i].color = "green";
        }
      }
      this.redrawSim();
    },
    redrawSim() {
      // console.log("update is happenning");
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

      var i;
      for (i = 0; i < this.shapes.length; i++) {
        //if (this.shapes[i].flash == true) {
        // this.ctx.fillStyle = "blue";
        // this.ctx.fillRect(10, 10, 80, 80);
        // var j = 0;
        // var x = 2;
        // for (j = 0; j < 10; j++) {
        //   x *= j;
        //   // this.ctx.fillStyle = "blue";
        //   // this.ctx.fillRect(10, 10, 80, 80);
        // }
        // console.log(x);
        // this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);

        // console.log("man pl work");
        // window.setInterval(function() {
        //   this.ctx.fillStyle = "blue";
        //   this.ctx.fillRect(10, 10, 80, 80);
        // }, 1000);
        //   }
        this.drawCircle(this.shapes[i]);
        //shapes[i].number=this.pnumber;
      }
      for (i = 0; i < this.arrows.length; i++) {
        this.canvas_arrow(
          this.arrows[i].fromx,
          this.arrows[i].fromy,
          this.arrows[i].tox,
          this.arrows[i].toy
        );
      }
    },
    changeColor() {
      this.pclass.color = this.pcolor;
    },
    addp() {
      var x = { text: "", color: "" };
      x.text = "p22";
      x.color = this.pcolor;
      this.items.push(x);
      console.log(this.pcolor);
    },
    addMach() {
      this.sbutton = "blue";
      this.sbutton1 = "white";
      this.addM = true;
      this.color = "green";
    },
    addQueue() {
      this.sbutton1 = "blue";
      this.sbutton = "white";
      this.addM = false;
      this.color = "yellow";
    },

    drawCircle(x) {
      // this.ctx.fillStyle = "blue";
      // this.ctx.fillRect(10, 10, 80, 80);
      // let radius = this.shapeBoundingBox_width / 2;

      // this.ctx.beginPath();
      // this.ctx.arc(
      //   this.shapeBoundingBox_left + this.shapeBoundingBox_width / 2,
      //   this.shapeBoundingBox_top + this.shapeBoundingBox_height / 2,
      //   radius,
      //   0,
      //   Math.PI * 2
      // );
      // this.ctx.stroke();
      // var no = parseInt(x.text.substring(1, x.length));
      //console.log("x flash : " + x.flash);
      this.ctx.beginPath();
      this.ctx.arc(x.x, x.y, 50, 0, 2 * Math.PI);
      this.ctx.stroke();
      if (x.text.charAt(0) == "Q") {
        this.ctx.fillStyle = x.color;
      } else {
        if (x.flash == true) {
          //   console.log("flash mf");
          //   this.ctx.fillStyle = "green";
          //   this.ctx.beginPath();
          //   this.ctx.font = "30px Arial";
          //   this.ctx.arc(x.x, x.y, 50, 0, 2 * Math.PI);
          //   this.ctx.fill();
          //   this.ctx.strokeText(x.text, x.x - 20, x.y + 10);
          //   x.flash = false;
          //   var i;
          //   for (i = 0; i < 10000000; i++) {
          //     var ruou = 0;
          //   }
          //   console.log(ruou);
          //   //   window.setTimeout(this.drawCircle(x), 100);
        }

        if (x.color == "green") {
          this.ctx.fillStyle = "green";
        } else {
          var r = x.color.substring(0, 3);
          var g = x.color.substring(3, 6);
          var p = x.color.substring(6, 9);
          var c = "rgb(" + r + "," + g + "," + p + ")";
          this.ctx.fillStyle = c;
          // console.log(c);
          //   console.log(this.ctx.fillStyle);
          // console.log("wtf l");
        }
      }
      this.ctx.beginPath();

      this.ctx.arc(x.x, x.y, 50, 0, 2 * Math.PI);
      this.ctx.fill();
      this.ctx.font = "30px Arial";
      this.ctx.strokeText(x.text, x.x - 20, x.y + 10);
      if (x.text.charAt(0) == "Q") {
        this.ctx.font = "20px Arial";

        this.ctx.strokeText(x.number, x.x - 25, x.y - 5);
      }
    },

    // drawArrow() {
    //   this.ctx.beginPath();
    //   this.ctx.moveTo(100, 100);
    //   this.ctx.lineTo(300, 560);
    //   this.ctx.lineTo(300, 560);

    //   this.ctx.stroke();
    // },
    connect() {
      if (this.a != "" && this.b != "") {
        if (
          !(
            (this.a.charAt(0) == "M" && this.b.charAt(0) == "Q") ||
            (this.b.charAt(0) == "M" && this.a.charAt(0) == "Q")
          )
        ) {
          alert(
            "invalid input : Machine can only be connected to Queue and the same otherwise"
          );
        } else {
          //   var machh=-1;
          //   var qu=-1;
          // // for(i=0; i<this.shapes.length; i++){
          //   if(shapes[i].text==this.a){
          //     machh=i;
          //   }
          //   else if(shapes[i].text==this.b){
          //     qu=i;
          //   }
          // }

          var x1 = -1;
          var y1 = 0;
          var x2 = -1;
          var y2 = 0;
          var i;
          var n = this.shapes.length;

          for (i = 0; i < n; i++) {
            if (this.shapes[i].text == this.a) {
              x1 = this.shapes[i].x;
              y1 = this.shapes[i].y;
            } else if (this.shapes[i].text == this.b) {
              x2 = this.shapes[i].x;
              y2 = this.shapes[i].y;
            }
          }
          if (x1 == -1 || x2 == -1) {
            alert("queue or machine doesnot exist");
          } else {
            var kk = parseInt(this.a.substring(1, this.a.length));
            var kk1 = parseInt(this.b.substring(1, this.b.length));
            // console.log("kk:" + kk);
            // console.log("kk1:" + kk1);
            if (
              (this.a.charAt(0) == "M" && this.machOut[kk - 1] == kk1) ||
              (this.a.charAt(0) == "Q" && this.machIn[kk1 - 1] == kk)
            ) {
              alert("connection already exist");
            } else if (
              (this.a.charAt(0) == "M" && this.machOut[kk - 1] != -1) ||
              (this.a.charAt(0) == "Q" && this.machIn[kk1 - 1] != -1)
            ) {
              alert("invalid connection");
            } else if (
              (this.a.charAt(0) == "M" && this.machIn[kk - 1] == kk1) ||
              (this.a.charAt(0) == "Q" && this.machOut[kk1 - 1] == kk)
            ) {
              alert("invalid connection");
            } else {
              if (this.a.charAt(0) == "M") {
                this.machOut[kk - 1] = kk1;
              } else {
                this.machIn[kk1 - 1] = kk;
              }
              // console.log(this.machIn);
              // console.log(this.machOut);
              // var m=(x1-x2)/(y1-y2);
              // var y3=m*()

              // if (
              //   Math.pow(x1 + 50 - x2, 2) + Math.pow(y2 - y1) <
              //   Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)
              // ) {
              //   x1 = x1 + 50;
              // }

              // if (
              //   Math.pow(x1 + 50 - x2, 2) + Math.pow(y2 - y1) <
              //   Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)
              // ) {
              //   x1 = x1 + 50;
              // }

              // if (
              //   Math.pow(x1 + 50 - x2, 2) + Math.pow(y2 - y1) <
              //   Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)
              // ) {
              //   x1 = x1 + 50;
              // }

              // if (
              //   Math.pow(x1 + 50 - x2, 2) + Math.pow(y2 - y1) <
              //   Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)
              // ) {
              //   x1 = x1 + 50;
              // }

              // if (
              //   Math.pow(x1 + 50 - x2, 2) + Math.pow(y2 - y1) <
              //   Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)
              // ) {
              //   x1 = x1 + 50;
              // }

              // if (x1 > x2 && Math.abs(y2 - y1) < 100) {
              //   x1 = x1 - 50;
              //   x2 = x2 + 50;
              // } else if (x1 <= x2 && Math.abs(y2 - y1) < 100) {
              //   x1 = x1 + 50;
              //   x2 = x2 + 50;
              // } else if (y2 < y1) {
              //   y1 = y1 - 50;
              //   y2 = y2 + 50;
              // } else {
              //   y2 = y2 - 50;
              //   y1 = y1 + 50;
              // }
              if (x2 > x1) {
                x1 = x1 + 50;
                x2 = x2 - 50;
              } else {
                x1 = x1 - 50;
                x2 = x2 + 50;
              }

              var u = { fromx: 0, fromy: 0, tox: 0, toy: 0 };
              u.fromx = x1;
              u.fromy = y1;
              u.tox = x2;
              u.toy = y2;
              this.arrows.push(u);
              this.canvas_arrow(x1, y1, x2, y2);
            }
          }
        }
      }
    },
    canvas_arrow(fromx, fromy, tox, toy) {
      //  console.log("fff");
      var headlen = 10; // length of head in pixels
      var dx = tox - fromx;
      var dy = toy - fromy;
      var angle = Math.atan2(dy, dx);
      this.ctx.moveTo(fromx, fromy);
      this.ctx.lineTo(tox, toy);
      this.ctx.lineTo(
        tox - headlen * Math.cos(angle - Math.PI / 6),
        toy - headlen * Math.sin(angle - Math.PI / 6)
      );
      this.ctx.moveTo(tox, toy);
      this.ctx.lineTo(
        tox - headlen * Math.cos(angle + Math.PI / 6),
        toy - headlen * Math.sin(angle + Math.PI / 6)
      );
      this.ctx.stroke();
    },

    ReactToMouseDown(event) {
      // this.canvas.style.cursor = "crosshair";
      // this.GetMousePosition(event.clientX, event.clientY);
      // this.mousedown_x = this.loc_x;
      // this.mousedown_y = this.loc_y;
      // this.SaveCanvasImage();
      // this.mx = event.clientX;
      // this.my = event.clientX; // this.dragging = true;
      if (!this.loading2 && !this.loading3) {
        var x = { text: "", color: "", x: 0, y: 0, flash: false, number: 0 };
        x.color = this.color;
        this.GetMousePosition(event.clientX, event.clientY);
        //  console.log("heu");
        this.ctx.fillStyle = this.color;

        this.ctx.beginPath();
        this.ctx.font = "30px Arial";
        x.x = this.mx;

        x.y = this.my;
        var i = 0;
        var flagg = false;
        for (i = 0; i < this.shapes.length; i++) {
          if (
            Math.pow(x.x - this.shapes[i].x, 2) +
              Math.pow(x.y - this.shapes[i].y, 2) <
            12100
          ) {
            alert("queue or machine is too close to others");
            flagg = true;
          }
        }
        if (!flagg) {
          this.ctx.arc(this.mx, this.my, 50, 0, 2 * Math.PI);
          this.ctx.fill();
          if (this.addM) {
            this.machIn.push(-1);
            this.machOut.push(-1);

            var t = "M" + this.mcounter;
            this.mcounter = this.mcounter + 1;
            this.ctx.strokeText(t, this.mx - 20, this.my + 10);
            x.text = t;
          } else {
            var t2 = "Q" + this.qcounter;
            this.qcounter = this.qcounter + 1;
            this.ctx.strokeText(t2, this.mx - 20, this.my + 20);
            this.ctx.font = "20px Arial";

            this.ctx.strokeText(0, this.mx - 25, this.my - 5);

            x.text = t2;
          }
          this.shapes.push(x);
          this.ctx.beginPath();
          this.ctx.arc(x.x, x.y, 50, 0, 2 * Math.PI);
          this.ctx.stroke();
          // console.log(this.shapes);
        }
      }
    },
    ReactToMouseMove(event) {
      console.log("heuff");
      console.log(event);

      // if (this.dragging == true) {
      //   this.GetMousePosition(event.clientX, event.clientY);

      //   this.RedrawCanvasImage();
      //   this.UpdateRubberbandOnMove();
      // }
    },
    ReactToMouseUp() {
      this.canvas.style.cursor = "default";
      this.GetMousePosition(event.clientX, event.clientY);
      this.RedrawCanvasImage();
      this.UpdateRubberbandOnMove();
      this.dragging = false;

      //////////////////////////
      // send shape to backend //

      ///////////////////////////////////
    },

    GetMousePosition(x, y) {
      // Get canvas size and position in web page
      let canvasSizeData = this.canvas.getBoundingClientRect();
      this.mx = x - canvasSizeData.left;
      this.my = y - canvasSizeData.top;

      // this.mx =
      //   (this.mx - canvasSizeData.left) *
      //   (this.canvas.width / canvasSizeData.width);
      // this.my =
      //   (this.my - canvasSizeData.top) *
      //   (this.canvas.height / canvasSizeData.height);
    },
  },
  mounted() {
    this.canvas = document.getElementById("my-canvas");
    this.ctx = this.canvas.getContext("2d");
    this.canvas.addEventListener("mousedown", this.ReactToMouseDown);
    //this.canvas.addEventListener("mousemove", this.ReactToMouseMove);
    // this.canvas.addEventListener("mouseup", this.ReactToMouseUp);
    this.canvas.width = this.innerWidth - 100;
    this.canvas.height = this.innerHeight - 100;
    axios.get("http://localhost:8091/getSessionKey").then((res) => {
      this.sessionKeysim = res.data;
    });
  },
};
</script>
<style>
.nc {
  background-color: rebeccapurple;
}
#my-canvas {
  border: 5px solid rgb(52, 0, 51);
  border-radius: 0px 0px 10px 20px;
  margin: auto;
  margin-left: 4px;
}
.sbutton {
}
</style>
