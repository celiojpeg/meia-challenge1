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
              :step="0.05"
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
                    ? -(0.8 * sliderLimits[questions[currentQuestion].category])
                    : -0.8
                "
                >Quase certamente não importante</b-slider-tick
              >
              <b-slider-tick
                :value="
                  'category' in questions[currentQuestion]
                    ? -(0.6 * sliderLimits[questions[currentQuestion].category])
                    : -0.6
                "
                >Provavelmente não importante</b-slider-tick
              >
              <b-slider-tick
                :value="
                  'category' in questions[currentQuestion]
                    ? -(0.4 * sliderLimits[questions[currentQuestion].category])
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
        <div class="column is-offset-10 is-2">
          <b-button
            type="is-primary"
            :disabled="!canAdvance"
            expanded
            @click="nextQuestion()"
            >Seguinte</b-button
          >
        </div>
      </div>
    </section>
  </div>
</template>

<script>
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
              description: "Preço é prioritário em relação a consumo e design?",
              value: "true",
            },
            {
              cf: 1.0,
              description: "Preço é prioritário em relação a consumo e design?",
              value: "false",
            },
            {
              cf: 0.0,
              description: "Consumo é prioritário em relação ao design?",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Consumo é prioritário em relação ao design?",
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
              description: "Disposto a pagar mais de 10.000€",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Disposto a pagar mais de 10.000€",
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
                "Prefere um carro mais antigo ao invés de um com mais kilometragem",
              value: "true",
            },
            {
              cf: 0.0,
              description:
                "Prefere um carro mais antigo ao invés de um com mais kilometragem",
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
              description: "O estado do veiculo é relevante",
              value: "true",
            },
            {
              cf: 0.0,
              description: "O estado do veiculo é relevante",
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
                "Prefere combustíveis fósseis em vez de um carro elétrico",
              value: "true",
            },
            {
              cf: 0.0,
              description:
                "Prefere combustíveis fósseis em vez de um carro elétrico",
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
              description: "A média de consumos é relevante",
              value: "true",
            },
            {
              cf: 0.0,
              description: "A média de consumos é relevante",
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
              description: "O consumo de emissões é um fator relevante",
              value: "true",
            },
            {
              cf: 0.0,
              description: "O consumo de emissões é um fator relevante",
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
              description: "A performance do veículo é relevante",
              value: "true",
            },
            {
              cf: 0.0,
              description: "A performance do veículo é relevante",
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
              description: "Irá realizar mais de 200 quilometros diários",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Irá realizar mais de 200 quilometros diários",
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
              description: "Quer introduzir modo de piloto automático",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Quer introduzir modo de piloto automático",
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
              description: "Dá preferência a um carro versátil a um familiar",
              value: "true",
            },
            {
              cf: 1.0,
              description: "Dá preferência a um carro versátil a um familiar",
              value: "false",
            },
            {
              cf: 0.0,
              description: "Dá preferência a um carro familiar a um desportivo",
              value: "true",
            },
            {
              cf: 0.0,
              description: "Dá preferência a um carro familiar a um desportivo",
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
              description: "Deseja um desportivo cFAMILIAR_7_LUGARESabrio",
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
            this.sliderLimits["Design"] = Math.abs(
              this.questions[this.currentQuestion].sliders[2].value
            );
          }

          if (this.currentQuestion == 11) {
            // SUV
            this.sliderLimits["Design"] = Math.abs(
              this.questions[this.currentQuestion].sliders[0].value
            );
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
    },
  },
};
</script>