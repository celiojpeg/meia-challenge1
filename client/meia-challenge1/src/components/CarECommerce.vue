<template>
  <div class="container">
    <section class="hero is-primary">
      <div class="hero-body">
        <p class="title">E-Commerce de Veículos - Sistema Pericial</p>
        <p class="subtitle">
          Dificuldades na procura do carro certo para si? Preencha as seguintes
          questões.
        </p>
      </div>
    </section>
    <section class="mt-5">
      <b-progress
        :value="
          finishedQuestions ? 100 : (currentQuestion / questions.length) * 100
        "
        show-value
        format="percent"
        :type="finishedQuestions ? 'is-success' : 'is-dark'"
      >
        <b-progress-bar v-if="finishedQuestions" :value="100" type="is-success"
          >Concluído!</b-progress-bar
        >
      </b-progress>
    </section>
    <section class="mt-5">
      <template v-if="!finishedQuestions">
        <p class="is-size-5" v-if="'category' in questions[currentQuestion]">
          {{ questions[currentQuestion].category }}
        </p>
        <p class="is-size-3 mb-4">{{ questions[currentQuestion].text }}</p>
        <template v-if="!questions[currentQuestion].boolAnswer">
          <div
            v-for="(slider, index) in questions[currentQuestion].sliders"
            :key="`slider-${index}`"
            class="my-5 py-5"
          >
            <b-field :label="slider.text">
              <b-slider
                size="is-medium"
                type="is-primary-dark"
                :step="
                  'category' in questions[currentQuestion]
                    ? 0.05 * sliderLimits[questions[currentQuestion].category]
                    : 0.05
                "
                :min="
                  'category' in questions[currentQuestion]
                    ? -sliderLimits[questions[currentQuestion].category]
                    : -1
                "
                :max="
                  'category' in questions[currentQuestion]
                    ? sliderLimits[questions[currentQuestion].category]
                    : 1
                "
                :tooltip="false"
                v-model="slider.value"
              >
                <b-slider-tick
                  :value="
                    'category' in questions[currentQuestion]
                      ? -sliderLimits[questions[currentQuestion].category]
                      : -1
                  "
                  >Definitivamente não importante</b-slider-tick
                >
                <b-slider-tick
                  :value="
                    'category' in questions[currentQuestion]
                      ? -(
                          0.8 *
                          sliderLimits[questions[currentQuestion].category]
                        )
                      : -0.8
                  "
                  >Quase certamente não importante</b-slider-tick
                >
                <b-slider-tick
                  :value="
                    'category' in questions[currentQuestion]
                      ? -(
                          0.6 *
                          sliderLimits[questions[currentQuestion].category]
                        )
                      : -0.6
                  "
                  >Provavelmente não importante</b-slider-tick
                >
                <b-slider-tick
                  :value="
                    'category' in questions[currentQuestion]
                      ? -(
                          0.4 *
                          sliderLimits[questions[currentQuestion].category]
                        )
                      : -0.4
                  "
                  >Talvez não importante</b-slider-tick
                >
                <b-slider-tick :value="0.0">Não sei</b-slider-tick>
                <b-slider-tick
                  :value="
                    'category' in questions[currentQuestion]
                      ? 0.4 * sliderLimits[questions[currentQuestion].category]
                      : 0.4
                  "
                  >Talvez importante</b-slider-tick
                >
                <b-slider-tick
                  :value="
                    'category' in questions[currentQuestion]
                      ? 0.6 * sliderLimits[questions[currentQuestion].category]
                      : 0.6
                  "
                  >Provavelmente importante</b-slider-tick
                >
                <b-slider-tick
                  :value="
                    'category' in questions[currentQuestion]
                      ? 0.8 * sliderLimits[questions[currentQuestion].category]
                      : 0.8
                  "
                  >Quase certamente importante</b-slider-tick
                >
                <b-slider-tick
                  :value="
                    'category' in questions[currentQuestion]
                      ? sliderLimits[questions[currentQuestion].category]
                      : 1
                  "
                  >Definitivamente importante</b-slider-tick
                >
              </b-slider>
            </b-field>
          </div>
        </template>
        <template v-else>
          <b-field>
            <b-radio-button
              v-model="questions[currentQuestion].answer"
              :native-value="true"
              type="is-success is-light is-outlined"
            >
              <b-icon icon="check"></b-icon>
              <span>Sim</span>
            </b-radio-button>

            <b-radio-button
              v-model="questions[currentQuestion].answer"
              :native-value="false"
              type="is-danger is-light is-outlined"
            >
              <b-icon icon="close"></b-icon>
              <span>Não</span>
            </b-radio-button>
          </b-field>
        </template>
        <div class="columns mt-5">
          <div v-if="currentQuestion != 0" class="column is-2">
            <b-button
              type="is-primary"
              expanded
              @click="currentQuestion = previousQuestion"
              >Anterior</b-button
            >
          </div>
          <div :class="nextQuestionClass">
            <b-button
              type="is-primary"
              :disabled="!canAdvance"
              expanded
              @click="nextQuestion()"
              >{{ currentQuestion == 15 ? "Concluir" : "Seguinte" }}</b-button
            >
          </div>
        </div>
      </template>
      <template v-else>
        <template v-if="isLoadingResults">
          <div class="columns is-centered">
            <div class="column is-half">
              <b-loading :is-full-page="false" :active="true"></b-loading>
            </div>
          </div>
        </template>
        <template v-else>
          <p class="is-size-2">
            Resultados
            <b-button
              type="is-primary"
              class="is-pulled-right"
              @click="reloadPage()"
              ><b-icon icon="reload" class="mr-2" />Nova avaliação</b-button
            >
          </p>
          <template v-if="conclusions.length == 0">
            <div class="columns is-centered">
              <div class="column is-half">
                Não foram encontrados resultados relevantes para as respostas
                dadas.
              </div>
            </div>
          </template>
          <template v-else>
            <b-collapse
              v-for="(conclusion, index) in conclusions"
              :key="`conclusion-${index}`"
              :aria-id="`conclusion-${index}`"
              class="panel"
              animation="slide"
              v-model="conclusion.isShowing"
              @open="getCarsInfo(index)"
            >
              <template #trigger>
                <div
                  class="panel-heading mb-5"
                  role="button"
                  :aria-controls="`conclusion-${index}`"
                >
                  {{ conclusion.description }} |
                  <span :class="getCFColor(conclusion.cf)"
                    >Certeza: {{ getCFLabel(conclusion.cf) }}</span
                  >
                  <small class="ml-5">({{ conclusion.cf.toFixed(2) }})</small>
                </div>
              </template>
              <div class="is-relative">
                <b-loading
                  :is-full-page="false"
                  v-model="conclusion.isLoading"
                ></b-loading>
                <div
                  v-for="(car, indexCar) in conclusion.listCars"
                  :key="`car-${index}-${indexCar}`"
                >
                  <div class="columns">
                    <div class="column">
                      <b-image :src="car.imageURL" />
                    </div>
                    <div class="column is-size-5">
                      <div class="mb-2">
                        <strong>Marca:</strong> {{ car.brand }}
                      </div>
                      <div class="mb-2">
                        <strong>Modelo:</strong> {{ car.model }}
                      </div>
                      <div class="mb-2">
                        <strong>Ano:</strong> {{ car.year }}
                      </div>
                      <div class="mb-2">
                        <strong>Preço Estimado: {{ car.price }}</strong>
                      </div>
                    </div>
                    <div class="column">
                      <b-button type="is-link" @click="openLink(car.link)"
                        ><b-icon icon="open-in-new" class="mr-2" /> Mais
                        Informação</b-button
                      >
                    </div>
                  </div>
                  <hr />
                </div>
              </div>
            </b-collapse>
          </template>
        </template>
      </template>
    </section>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CarECommerce",
  data() {
    return {
      totalQuestions: 16,
      previousQuestion: 0,
      currentQuestion: 0,
      finishedQuestions: false,
      isLoadingResults: false,
      sliderLimits: {
        Preço: 1,
        Consumo: 1,
        Design: 1,
      },
      questions: [
        {
          // 0 - PRECO_PRIORITARIO CONSUMO_PRIORITARIO
          text: "Avalie cada uma das seguintes características baseando-se na certeza que dá à importância de cada uma no veículo deseja.",
          boolAnswer: false,
          sliders: [
            {
              text: "Preço",
              value: 0.0,
            },
            {
              text: "Consumo",
              value: 0.0,
            },
            {
              text: "Design",
              value: 0.0,
            },
          ],
          evidences: [
            {
              cf: 0.0,
              description: "Preco e prioritario em relacao a consumo e design",
              value: "true",
            },
            {
              cf: 1.0,
              description: "Preco e prioritario em relacao a consumo e design",
              value: "false",
            },
            {
              cf: 0.0,
              description: "Consumo e prioritario em relacao ao design",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Consumo e prioritario em relacao ao design",
              value: "false",
            },
          ],
        },
        {
          // 1 - QUAL_O_PRECO
          text: "Está disposto a pagar mais de 10.000€ pelo veículo?",
          boolAnswer: true,
          category: "Preço",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "Disposto a pagar mais de 10.000",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Disposto a pagar mais de 10.000",
              value: "false",
            },
          ],
        },
        {
          // 2 - ANTIGUIDADE_QUILOMETRAGEM
          text: "É preferível mais antiguidade do que mais quilometragem no veículo?",
          boolAnswer: true,
          category: "Preço",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description:
                "Prefere um carro mais antigo ao inves de um com mais kilometragem",
              value: "true",
            },
            {
              cf: 0.0,
              description:
                "Prefere um carro mais antigo ao inves de um com mais kilometragem",
              value: "false",
            },
          ],
        },
        {
          // 3 - IMPOSTO_VEICULO
          text: "Deseja evitar o imposto anual?",
          boolAnswer: true,
          category: "Preço",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "Quer ter em conta o imposto anual",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Quer ter em conta o imposto anual",
              value: "false",
            },
          ],
        },
        {
          // 4 - ESTADO_VEICULO
          text: "O estado do veículo é relevante?",
          boolAnswer: true,
          category: "Preço",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "O estado do veiculo e relevante",
              value: "true",
            },
            {
              cf: 0.0,
              description: "O estado do veiculo e relevante",
              value: "false",
            },
          ],
        },
        {
          // 5 - COMBUSTIVEIS_FOSSEIS
          text: "Prefere um veículo a combustíveis fósseis em vez de elétrico?",
          boolAnswer: true,
          category: "Consumo",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description:
                "Prefere combustiveis fosseis em vez de um carro eletrico",
              value: "true",
            },
            {
              cf: 0.0,
              description:
                "Prefere combustiveis fosseis em vez de um carro eletrico",
              value: "false",
            },
          ],
        },
        {
          // 6 - MEDIA_CONSUMO
          text: "Avalie a sua certeza em relação à importância que dá a que o veículo utilize os seguintes combustíveis.",
          boolAnswer: false,
          category: "Consumo",
          sliders: [
            {
              text: "Gasóleo",
              value: 0.0,
            },
            {
              text: "Gasolina",
              value: 0.0,
            },
          ],
          evidences: [
            {
              cf: 0.0,
              description: "A media de consumos e relevante",
              value: "true",
            },
            {
              cf: 0.0,
              description: "A media de consumos e relevante",
              value: "false",
            },
          ],
        },
        {
          // 7 - EMISSOES
          text: "A produção de emissões é um fator relevante?",
          boolAnswer: true,
          category: "Consumo",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "O consumo de emissoes e um fator relevante",
              value: "true",
            },
            {
              cf: 0.0,
              description: "O consumo de emissoes e um fator relevante",
              value: "false",
            },
          ],
        },
        {
          // 8 - PERFORMANCE
          text: "A performance do veículo é um fator relevante?",
          boolAnswer: true,
          category: "Consumo",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "A performance do veiculo e relevante",
              value: "true",
            },
            {
              cf: 0.0,
              description: "A performance do veiculo e relevante",
              value: "false",
            },
          ],
        },
        {
          // 9 - MAIS_200KM
          text: "Irá realizar mais de 200 KM diários?",
          boolAnswer: true,
          category: "Consumo",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "Ira realizar mais de 200 quilometros diarios",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Ira realizar mais de 200 quilometros diarios",
              value: "false",
            },
          ],
        },
        {
          // 10 - PILOTO_AUTOMATICO
          text: "Deseja piloto automático?",
          boolAnswer: true,
          category: "Consumo",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "Quer introduzir modo de piloto automatico",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Quer introduzir modo de piloto automatico",
              value: "false",
            },
          ],
        },
        {
          // 11 - CARRO_VERSATIL CARRO_FAMILIAR
          text: "Avalie a sua certeza em relação à importância que dá aos seguintes perfis de veículos.",
          boolAnswer: false,
          category: "Design",
          sliders: [
            {
              text: "Perfil Versátil",
              value: 0.0,
            },
            {
              text: "Perfil Familiar",
              value: 0.0,
            },
            {
              text: "Perfil Desportivo",
              value: 0.0,
            },
          ],
          evidences: [
            {
              cf: 0.0,
              description: "Da preferencia a um carro versatil a um familiar",
              value: "true",
            },
            {
              cf: 1.0,
              description: "Da preferencia a um carro versatil a um familiar",
              value: "false",
            },
            {
              cf: 0.0,
              description: "Da preferencia a um carro familiar a um desportivo",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Da preferencia a um carro familiar a um desportivo",
              value: "false",
            },
          ],
        },
        {
          // 12 - TIPO_SUV_OFF_ROAD
          text: "Avalie a sua certeza em relação à importância que daria aos seguintes tipos de SUV.",
          boolAnswer: false,
          category: "Design",
          sliders: [
            {
              text: "SUV Off-Road",
              value: 0.0,
            },
            {
              text: "SUV On-Road",
              value: 0.0,
            },
          ],
          evidences: [
            {
              cf: 0.0,
              description: "Prefere um SUV off-road a on-road",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Prefere um SUV off-road a on-road",
              value: "false",
            },
          ],
        },
        {
          // 13 - DESPORTIVO_2PORTAS
          text: "Desejaria um desportivo de 2 portas?",
          boolAnswer: true,
          category: "Design",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "Deseja um desportivo de 2 portas",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Deseja um desportivo de 2 portas",
              value: "false",
            },
          ],
        },
        {
          // 14 - DESPORTIVO_CABRIO
          text: "Desejaria um desportivo cábrio?",
          boolAnswer: true,
          category: "Design",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "Deseja um desportivo cabrio",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Deseja um desportivo cabrio",
              value: "false",
            },
          ],
        },
        {
          // 15 - FAMILIAR_7_LUGARES
          text: "Se desejar um carro familiar este deve ter 7 lugares?",
          boolAnswer: true,
          category: "Design",
          answer: null,
          evidences: [
            {
              cf: 0.0,
              description: "O seu carro familiar deve ter 7 lugares",
              value: "true",
            },
            {
              cf: 0.0,
              description: "O seu carro familiar deve ter 7 lugares",
              value: "false",
            },
          ],
        },
      ],
      conclusions: [],
    };
  },
  computed: {
    canAdvance: function () {
      if (this.questions[this.currentQuestion].boolAnswer) {
        return this.questions[this.currentQuestion].answer != null;
      }

      for (
        let i = 0;
        i < this.questions[this.currentQuestion].sliders.length;
        i++
      ) {
        if (this.questions[this.currentQuestion].sliders[i].value != 0.0) {
          return true;
        }
      }

      return false;
    },
    nextQuestionClass: function () {
      if (this.currentQuestion == 0) {
        return { column: true, "is-offset-10": true, "is-2": true };
      }

      return { column: true, "is-offset-8": true, "is-2": true };
    },
  },
  methods: {
    nextQuestion() {
      this.previousQuestion = this.currentQuestion;
      if (!this.questions[this.currentQuestion].boolAnswer) {
        if (this.questions[this.currentQuestion].sliders.length == 3) {
          this.processTripleSlider();

          if (this.currentQuestion == 0) {
            this.sliderLimits["Consumo"] = Math.abs(
              this.questions[this.currentQuestion].sliders[1].value
            );

            if (this.sliderLimits["Consumo"] == 0) {
              this.sliderLimits["Consumo"] = 0.1;
            }

            this.sliderLimits["Design"] = Math.abs(
              this.questions[this.currentQuestion].sliders[2].value
            );

            if (this.sliderLimits["Design"] == 0) {
              this.sliderLimits["Design"] = 0.1;
            }
          }

          if (this.currentQuestion == 11) {
            // SUV
            this.sliderLimits["Design"] = Math.abs(
              this.questions[this.currentQuestion].sliders[0].value
            );

            if (this.sliderLimits["Design"] == 0) {
              this.sliderLimits["Design"] = 0.1;
            }
          }
        } else if (this.questions[this.currentQuestion].sliders.length == 2) {
          this.processDoubleSlider();
        }

        this.currentQuestion++;
        return;
      }

      this.processBoolAnswer();
    },
    processTripleSlider() {
      this.questions[this.currentQuestion].evidences[0].cf =
        this.questions[this.currentQuestion].sliders[0].value;

      this.questions[this.currentQuestion].evidences[2].cf =
        this.questions[this.currentQuestion].sliders[1].value;

      this.questions[this.currentQuestion].evidences[3].cf =
        this.questions[this.currentQuestion].sliders[2].value;
    },
    processDoubleSlider() {
      this.questions[this.currentQuestion].evidences[0].cf =
        this.questions[this.currentQuestion].sliders[0].value;

      this.questions[this.currentQuestion].evidences[1].cf =
        this.questions[this.currentQuestion].sliders[1].value;
    },
    processBoolAnswer() {
      if (this.questions[this.currentQuestion].answer) {
        this.questions[this.currentQuestion].evidences[0].cf = 1.0;
        this.questions[this.currentQuestion].evidences[1].cf = -1.0;
      } else {
        this.questions[this.currentQuestion].evidences[0].cf = -1.0;
        this.questions[this.currentQuestion].evidences[1].cf = 1.0;
      }

      switch (this.currentQuestion) {
        case 1: // QUAL_O_PRECO
          if (this.questions[this.currentQuestion].answer) {
            this.totalQuestions = 13;
            this.currentQuestion = 5; // COMBUSTIVEIS_FOSSEIS
            this.minusOneAllEvidences(2, 4);
          } else {
            this.totalQuestions = 16;
            this.currentQuestion = 2; // ANTIGUIDADE_QUILOMETRAGEM
          }

          break;
        case 2: // ANTIGUIDADE_QUILOMETRAGEM
          if (this.questions[this.currentQuestion].answer) {
            this.currentQuestion = 3; // IMPOSTO_VEICULO
            this.minusOneAllEvidences(4);
          } else {
            this.currentQuestion = 4; // ESTADO_VEICULO
            this.minusOneAllEvidences(3);
          }
          break;
        case 3: // IMPOSTO_VEICULO
        case 4: // ESTADO_VEICULO
          this.currentQuestion = 5; // COMBUSTIVEIS_FOSSEIS
          break;
        case 5: // 5 - COMBUSTIVEIS_FOSSEIS
          if (this.questions[this.currentQuestion].answer) {
            this.currentQuestion = 6; // MEDIA_CONSUMO
            this.minusOneAllEvidences(9, 10);
          } else {
            this.currentQuestion = 9; // MAIS_200KM
            this.minusOneAllEvidences(6, 8);
          }
          break;
        case 7: // EMISSOES
          this.currentQuestion = 8;
          break;
        case 8: // PERFORMANCE
          this.currentQuestion = 11; // MAIS_200KM
          break;
        case 9: // MAIS_200KM
          if (this.questions[this.currentQuestion].answer) {
            this.currentQuestion = 10; // PILOTO_AUTOMATICO
          } else {
            this.currentQuestion = 11; // CARRO_VERSATIL CARRO_FAMILIAR
            this.minusOneAllEvidences(10);
          }
          break;
        case 10:
          this.currentQuestion = 11;
          break;
        case 13: // DESPORTIVO_2PORTAS
          if (this.questions[this.currentQuestion].answer) {
            this.currentQuestion = 14; // DESPORTIVO_CABRIO
          } else {
            this.currentQuestion = 15; // FAMILIAR_7_LUGARES
            this.minusOneAllEvidences(14);
          }
          break;
        case 14: // DESPORTIVO_CABRIO
          this.currentQuestion = 15; // FAMILIAR_7_LUGARES
          break;
        case 15:
          this.finishedQuestions = true;
          this.getResults();
          break;
      }
    },
    minusOneAllEvidences(startQuestion, endQuestion = startQuestion) {
      for (let i = startQuestion; i <= endQuestion; i++) {
        this.questions[i].evidences.forEach((evidence) => {
          evidence.cf = -1;
        });
      }
    },
    getResults() {
      this.isLoadingResults = true;

      let evidences = [];

      this.questions.forEach((question) => {
        evidences = evidences.concat(question.evidences);
      });

      axios
        .post("http://localhost:8081/fire-rules", evidences)
        .then((response) => {
          this.conclusions = response.data.filter(
            (conclusion) => conclusion.cf >= 0.1
          );

          this.conclusions.sort((a, b) => {
            return b.cf - a.cf;
          });

          this.conclusions.forEach((conclusion, index) => {
            conclusion.isLoading = false;
            if (conclusion.cf > 0.2) {
              this.getCarsInfo(index);
              conclusion.isShowing = true;
              conclusion.hasInfo = true;
            } else {
              conclusion.isShowing = false;
              conclusion.hasInfo = false;
            }
          });

          this.isLoadingResults = false;
        });
    },
    getCFLabel(cf) {
      if (cf <= 0.2) {
        return "Desconhecido";
      }

      if (cf <= 0.4) {
        return "Talvez";
      }

      if (cf <= 0.6) {
        return "Provável";
      }

      if (cf <= 0.8) {
        return "Quase certamente";
      }

      return "Certamente";
    },
    getCFColor(cf) {
      if (cf <= 0.2) {
        return "has-text-dark";
      }

      if (cf <= 0.4) {
        return "has-text-danger";
      }

      if (cf <= 0.6) {
        return "has-text-warning";
      }

      if (cf <= 0.8) {
        return "has-text-success";
      }

      return "has-text-info";
    },
    getCarsInfo(index) {
      if (this.conclusions[index].hasInfo) {
        return;
      }

      this.conclusions[index].isLoading = true;

      const carLinks = this.conclusions[index].listCars.map((car) => car.link);

      axios
        .post("http://localhost:8081/car-info", carLinks)
        .then((response) => {
          response.data.forEach((carInfo, indexCar) => {
            this.conclusions[index].listCars[indexCar].imageURL =
              carInfo.imageURL;

            this.conclusions[index].listCars[indexCar].price = carInfo.price;
          });

          this.conclusions[index].hasInfo = true;
          this.conclusions[index].isLoading = false;
          this.$forceUpdate();
        });
    },
    openLink(link) {
      window.open(link);
    },
    reloadPage() {
      location.reload();
    },
  },
};
</script>